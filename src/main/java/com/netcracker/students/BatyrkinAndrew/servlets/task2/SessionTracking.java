package com.netcracker.students.BatyrkinAndrew.servlets.task2;

import com.netcracker.students.BatyrkinAndrew.help.Accounts;
import com.netcracker.students.BatyrkinAndrew.help.MyRequest;
import com.netcracker.students.BatyrkinAndrew.help.pojo.Profile;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/tasks/task2/session")
public class SessionTracking extends HttpServlet {

    @SuppressWarnings("Duplicates")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(true);
        String login = request.getParameter("login");
        String password = request.getParameter("password");


        String loginKey = "Login";
        String passwordKey = "Password";
        if (session.isNew()) {
            session.setAttribute(loginKey, login);
            session.setAttribute(passwordKey, password);
        } else {
            request.setAttribute("login", session.getAttribute(loginKey));
            request.setAttribute("password", session.getAttribute(passwordKey));
        }



        PrintWriter out = response.getWriter();




//        statusOut(out, login, password);
        Accounts.init(getServletContext().getRealPath("/WEB-INF/classes/users.txt"));
        if (!Accounts.isValidLogin(login)) {
            response.sendRedirect("/tasks/task2/create_account.html");
        } else {
            out.println(new MyRequest(Accounts.isValidData(new Profile(login, password)), login).getOut());

        }

        out.close();
    }

}
