package com.dummies.web;

import com.dummies.exception.InvalidSessionException;
import com.dummies.model.DummyUser;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * User: atiq2
 * Date: Dec 10, 2007
 * Time: 12:47:09 PM
 * To change this template use File | Settings | File Templates.
 */

/**
 * Utility methods needed for authentication.
 */
public class Auth {
    private static final Logger logger = Logger.getLogger(Auth.class);

    public static void login(DummyUser user, HttpServletRequest request) {
        if (user == null) {
            throw new RuntimeException("Null user");
        }

        HttpSession session = request.getSession();
        Hibernate.initialize(user);
//        Hibernate.initialize(user.getBranch());
        session.setAttribute(SessionKeys.AUTH, user);
        request.setAttribute(RequestKeys.AUTH_ATTRIBUE, user);
    }

    public static void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    /**
     * Retrieve the logged in user from HTTP session.
     */
    public static DummyUser getLoggedInUser(HttpServletRequest request) {
        DummyUser user = getUserNullable(request);

        if (user == null) {
            logger.error("No user object found in session");
            throw new InvalidSessionException("No user object found in session");
        }

        return user;
    }

    public static DummyUser getLoggedInUser() throws InvalidSessionException {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        DummyUser user = (DummyUser) attributes.getAttribute(RequestKeys.AUTH_ATTRIBUE, RequestAttributes.SCOPE_REQUEST);

        if (user == null) {
            logger.error("No user object found in request");
            throw new InvalidSessionException("No user object found in request");
        }
        return user;
    }

    public static DummyUser getUserNullable(HttpServletRequest request) {
        return (DummyUser) request.getAttribute(RequestKeys.AUTH_ATTRIBUE);
    }

    /**
     * Check if there is a logged in user.
     */
    public static boolean isLoggedIn(HttpServletRequest request) {
        DummyUser user = getUserNullable(request);

        return (user != null);
    }
}
