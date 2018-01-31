package com.dummies.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author : Atiqur Rahman
 * @since : 12/22/11
 */
@Controller
@RequestMapping(value = "/refreshSession")
public class RefreshSessionController {

    @RequestMapping(method = RequestMethod.GET)
    public String viewSmg(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (request.getSession(false) != null) {
            send(response, "OK");
        } else {
            send(response, "EXPIRED");
        }

        return null;
    }

    public static void send(HttpServletResponse response, String message) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"status\":\"").append(message).append("\"}");

        byte[] buffer = sb.toString().getBytes("UTF-8");

        response.setContentType("application/json");
        response.setContentLength(buffer.length);
        response.setCharacterEncoding("UTF-8");

        response.setHeader("Pragma", "no-cache");
        response.addHeader("Cache-Control", "must-revalidate");
        response.addHeader("Cache-Control", "no-cache");
        response.addHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);

        OutputStream out = response.getOutputStream();
        out.write(buffer);
        out.close();
    }
}
