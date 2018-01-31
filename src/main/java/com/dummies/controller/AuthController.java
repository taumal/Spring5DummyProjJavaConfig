package com.dummies.controller;

import com.dummies.command.DashboardModuleCmd;
import com.dummies.dao.CommonDao;
import com.dummies.exception.AuthException;
import com.dummies.model.DummyUser;
import com.dummies.web.Auth;
import com.dummies.util.PasswordUtils;
import com.dummies.util.Utils;
import com.dummies.web.SessionKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author taumal
 * @since 1/31/18 4:26 PM
 */
@Controller
@RequestMapping(value = "/login")
public class AuthController {
    @SuppressWarnings({"UnusedDeclaration"})
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    @Qualifier("commonDao")
    private CommonDao commonDao;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setRequiredFields("userName", "password");
    }

    @RequestMapping(method = RequestMethod.GET)
    protected String show(HttpServletRequest request, ModelMap model) throws Exception {
        if (Auth.isLoggedIn(request)) {
            return "redirect:/";
        }
        model.put("auth", new DummyUser());

        return getFormView();
    }

    @RequestMapping(method = RequestMethod.POST)
    protected String submit(@ModelAttribute(value = "auth") DummyUser auth,
                            BindingResult result, HttpServletRequest request){
        try{
            if (Utils.isNotEmpty(auth.getUserName()) && Utils.isNotEmpty(auth.getPassword())) {

                DummyUser dbUser = commonDao.getUser(auth.getUserName());
                boolean isValidPassword = PasswordUtils.verifyPassword(auth.getPassword(), dbUser.getPasswordHash(),
                        dbUser.getPasswordSalt());

                if (!dbUser.getUserName().equals(auth.getUserName()) || !isValidPassword) {
                    throw new RuntimeException("User name and password are case sensitive");
                }

                if (dbUser.getStatus() == DummyUser.Status.INACTIVE.getValue()) {
                    result.rejectValue("password", "", "Inactive User");
                    return getFormView();
                }

                DummyUser targetUser = getTargetUser(dbUser, auth.getUserName());

                Auth.login(targetUser, request);

                List<DashboardModuleCmd> list = new ArrayList<DashboardModuleCmd>();
                list.addAll(LinkController.getLinkMap().values());
                request.getSession().setAttribute(SessionKeys.MODULE_LINKS, list);

                return "redirect:/";
            }
        } /*catch (AuthException ae) {
            logger.debug(ae.getMessage());
            result.rejectValue("userName", ae.getMessage(), "Username Invalid");
        }*/ catch (Exception e) {
            logger.debug("INCORRECT USERNAME / PASSWORD:", e);
            result.rejectValue("password", "pass.incorrect", "Login failed!");

            System.out.println("ERROR: " + e);
        }
        return getFormView();
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    protected String doLogout(HttpServletRequest request, ModelMap model) throws Exception {
        Auth.logout(request);
        model.put("auth", new DummyUser());
        return "redirect:/login";
    }

    protected DummyUser getTargetUser(DummyUser cmd, String targetUsername) throws AuthException {
        return cmd;
    }

    protected String getFormView() {
        return "auth/login";
    }
}
