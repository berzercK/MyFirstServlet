package com.netcracker.Gleb;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/sessions")
public class SessionTracking extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(true);

        Date createTime = new Date(session.getCreationTime());

        Date lastAccessTime = new Date(session.getLastAccessedTime());

        String title = "Welcome Back to my website";
        Integer visitCount = 0;
        String visitCountKey = new String("visitCount");
        String userIDKey = new String("userID");
        String userID = new String("ABCD");

        if (session.isNew()) {
            title = "Welcome to my website";
            session.setAttribute(userIDKey, userID);
        } else {
            visitCount = (Integer) session.getAttribute(visitCountKey);
            visitCount = visitCount + 1;
            userID = (String) session.getAttribute(userIDKey);
        }
        session.setAttribute(visitCountKey, visitCount);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        StringBuilder sb = new StringBuilder("<html>");
        sb.append("<head><title>").append(title).append("</head></title>");
        sb.append("<body>");

        sb.append("<h1>" + "Visit count: ").append(visitCount).append("</h1><br>");
        sb.append("<h1>" + "Visit count key: ").append(visitCountKey).append("</h1><br>");
        sb.append("<h1>" + "UserId key: ").append(userIDKey).append("</h1><br>");
        sb.append("<h1>" + "UserId: ").append(userID).append("</h1><br>");
        sb.append("<h1>" + "Create time: ").append(createTime).append("</h1><br>");
        sb.append("<h1>" + "Last access time: ").append(lastAccessTime).append("</h1><br>");
        sb.append("<h1>" + "JSESSION_ID: ").append(request.getCookies()[0].getValue()).append("</h1><br>");

        sb.append("</body></html>");

        out.print(sb);

    }
}
