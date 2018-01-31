package com.dummies.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author taumal
 * @since 1/31/18 7:00 PM
 */
public class Utils {
    public static boolean isNullOrEmpty(String str) {
        return ((str == null) || (str.trim().length() == 0));
    }

    public static boolean isNotEmpty(String str) {
        return !isNullOrEmpty(str);
    }

    public static String getEmail(String emailStr){
        String REGEX = "\\s*(\\s|,)\\s*";
        String email="";

        if(emailStr != null) {
            String[] emails = emailStr.split(REGEX);
            for (String eml : emails) {
                if (checkValidEmail(eml)) {
                    email = eml;
                    break;
                }
            }
        }

        return email;
    }

    public static boolean checkValidEmail(String email){
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return   matcher.matches();
    }
}
