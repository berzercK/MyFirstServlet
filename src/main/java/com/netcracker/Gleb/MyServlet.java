package com.netcracker.Gleb;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // Create cookies for first and last names.
        Cookie firstName = new Cookie("first_name", req.getParameter("first_name"));
        Cookie lastName = new Cookie("last_name", req.getParameter("last_name"));

        // Set expiry date after 24 Hrs for both the cookies.
        firstName.setMaxAge(60*60*24);
        lastName.setMaxAge(60*60*24);

        // Add both the cookies in the response header.
        resp.addCookie( firstName );
        resp.addCookie( lastName );

        // Set response content type
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();

        StringBuilder sb = new StringBuilder("<html>");

        sb.append("<body>");
        sb.append("<h1>"+"First name-->"+req.getParameter("first_name")+"</h1>");
        sb.append("</br>");

        sb.append("<h1>"+"Last name-->"+req.getParameter("last_name")+"</h1>");
        sb.append("</br>");

        sb.append("</body>");
        sb.append("</html>");

        out.print(sb);

        out.close();

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.doGet(req, resp);
    }


}
