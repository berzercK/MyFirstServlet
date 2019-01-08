package com.netcracker.students.BatyrkinAndrew.help;

import javax.servlet.http.Cookie;
import java.io.PrintWriter;

public class MyCookies {
    private String login;
    private String password;
    private boolean isSuccessValid;

    public MyCookies(Cookie[] reqCookies) {
        Cookie[] reqCookies1 = new Cookie[reqCookies.length];
        initField(reqCookies);
    }

    private void initField(Cookie[] reqCookies) {
        for (Cookie c :
                reqCookies) {
            String loginKey = "Login";
            if (c.getName().equals(loginKey)) {
                login = c.getValue();
            }
            String passwordKey = "Password";
            if (c.getName().equals(passwordKey)) {
                password = c.getValue();
            }
            String isSuccessValidKey = "isSuccessValid";
            if (c.getName().equals(isSuccessValidKey)) {
                isSuccessValid = c.getValue().equals("true");
            }
        }
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isSuccessValid() {
        return isSuccessValid;
    }

    public static void writeCookies(PrintWriter out, Cookie[] cookies) {
        if (cookies != null) {
            out.print("<br>" + "<br>" + "Current cookies: " + "<br>");
            out.print("<br>" + "Number cookies: " + cookies.length + "<br>");
            for (Cookie c :
                    cookies) {
                out.println(c.getName() + " = " + c.getValue() + "<br>");
            }
        }
    }
}
