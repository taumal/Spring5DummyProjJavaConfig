package com.dummies.filter;

import com.dummies.controller.RefreshSessionController;
import com.dummies.model.DummyUser;
import com.dummies.web.Forwards;
import com.dummies.web.RequestKeys;
import com.dummies.web.SessionKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * User: atiq2
 * Date: Dec 9, 2007
 */

public class AuthFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    public static final String ENCODING = "UTF-8";

    private static final Logger profLog = LoggerFactory.getLogger("org.quantum.qmail.common.web.PROFILER");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        /* do nothing */
    }

    @Override
    public void destroy() {
        /* do nothing */
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest hsreq = (HttpServletRequest) request;
        HttpServletResponse hsres = (HttpServletResponse) response;
        final String uri = hsreq.getRequestURI().substring(hsreq.getContextPath().length());

        if (skipFilter(uri)) {
            chain.doFilter(request, response);
            return;
        }
        if (hsreq.getCharacterEncoding() == null) {
            hsreq.setCharacterEncoding(ENCODING);
        }

        DummyUser user = null;
        HttpSession session = hsreq.getSession(false); // session will null if it is aleady expired
        if (session != null) {
            user = (DummyUser) session.getAttribute(SessionKeys.AUTH);
            if (user == null) {
                session.invalidate();
                session = null;
            }

        }
        if (session == null) {
            String ajaxHeader = ((HttpServletRequest) request).getHeader("X-Requested-With");
            if ("XMLHttpRequest".equals(ajaxHeader))
            {
                hsres.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "AJAX_SESSION_EXPIRED");
                return;
            }

            logger.debug("Caught session expired case: URI=[" + uri + "], IP=" + hsreq.getRemoteAddr());
            if (uri.equals("/common/refreshSession")) {
                RefreshSessionController.send((HttpServletResponse) response, "EXPIRED");
                return;
            }

            if (!uri.equals(Forwards.AUTH_VIEW_PATH) &&
                    !uri.equals(Forwards.MASTER_LOGIN_VIEW_PATH)) {
                hsres.sendRedirect(hsreq.getContextPath() + Forwards.AUTH_VIEW_PATH + "?sessionError=true");
                return;
            }

            chain.doFilter(request, response);
            return;
        }
        final long start = System.currentTimeMillis();

        /*
        * Add MDC.  The session ID can be too long, so we are
        * deriving a smaller ID to identify the session, hope it
        * doesn't collide.
        */

        int sh = session.getId().hashCode() % 10000;
        String mdc = user.getUserName() + ":" + sh;

        final String method = hsreq.getMethod();
        final String shortMethod = "GET".equals(method) ? "G" : "P";

        MDC.put("userID", mdc);

        /*
        * We must ensure NDC is popped, even if request is aborted in
        * the middle or we get other exceptions.
        */

        try {
            /*
            * In slow response situations, a user can click logout while a request is being processed.  This spits out
            * a nasty error from Auth.  So now we are putting the user in the request, and from Auth, we will
            * only access the user from the request scope.
            */
            request.setAttribute(RequestKeys.AUTH_ATTRIBUE, user);
            long responseTime;
            chain.doFilter(request, response);

            if (profLog.isDebugEnabled() && !"/chat".equals(uri)) {
                /* Time will not be printed if we get an exception */
                long stop = System.currentTimeMillis();
                responseTime = stop - start;
                StringBuilder sb = new StringBuilder();

                sb.append("URI=[")
                        .append(uri)
                        .append("], ").append(shortMethod)
                        .append(", time=")
                        .append(responseTime)
                        .append("ms");
                profLog.debug(sb.toString());
            }
        } finally {
            MDC.get("userName");
        }
    }

    private boolean skipFilter(String uri) {
        return uri.startsWith("/resources/css/")
                || uri.startsWith("/resources/img/")
                || uri.startsWith("/js/")
                || uri.endsWith(".js")
                || uri.endsWith(".css")
                ;
    }
}
