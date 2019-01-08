package com.netcracker.Gleb;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/cookies")
@WebServlet("/tasks/task2/cookies")
public class ReadCookies extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Cookie cookie;
        Cookie[] cookies;

        cookies = request.getCookies();
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        StringBuilder sb = new StringBuilder("<html>");
        sb.append("<body>");

        if( cookies != null ) {
            sb.append("<h2> Found Cookies Name and Value</h2>");

            for (Cookie cookie1 : cookies) {
                cookie = cookie1;
                sb.append("Name : ").append(cookie.getName()).append(",  ");
                sb.append("Value: ").append(cookie.getValue()).append(" <br/>");
            }
        } else {
            sb.append("<h2>No cookies founds</h2>");
        }
        sb.append("</body>");
        sb.append("</html>");

        out.print(sb);
    }
}
