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
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Accounts.init(getServletContext().getRealPath("/WEB-INF/classes/users.txt"));

        String login = "";
        String password = "";
        boolean isSuccessValid = false;
        boolean[] isValidData = new boolean[2];
        boolean[] isValidDataNewProfile = new boolean[2];

        String loginKey = "Login";
        String passwordKey = "Password";
        String isSuccessValidKey = "isSuccessValid";

        String newLogin = "";
        String newPassword = "";

        if (session.isNew()) {
            session.setAttribute(loginKey, login);
            session.setAttribute(passwordKey, password);
            session.setAttribute(isSuccessValidKey, false);

            login = request.getParameter("login");
            password = request.getParameter("password");
            isValidData = Accounts.isValidData(new Profile(login, password));
            if (isValidData[0] && isValidData[1]) {
                isSuccessValid = true;
            }
            if (isSuccessValid) {
                session.setAttribute(loginKey, login);
                session.setAttribute(passwordKey, password);
                session.setAttribute(isSuccessValidKey, true);
            } else {
                session.setAttribute(isSuccessValidKey, false);
            }
        } else {
            newLogin = request.getParameter("login");
            newPassword = request.getParameter("password");

            if (newLogin.length() == 0 && newPassword.length() == 0) {
                if (isSuccessValid = (boolean) session.getAttribute(isSuccessValidKey)) {
                    login = (String) session.getAttribute(loginKey);
                    password = (String) session.getAttribute(passwordKey);
                    out.println(new MyRequest(Accounts.isValidData(new Profile(login, password)), login).
                            getOut());
//                    out.println(new MyRequest(true, login).getOut());
                }
            } else {
                if (!Accounts.isValidLogin(newLogin)) {
                    response.sendRedirect("/tasks/task2/create_account.html");
                } else {
                    isValidDataNewProfile = Accounts.isValidData(new Profile(newLogin, newPassword));
                    if (isValidDataNewProfile[0] && isValidDataNewProfile[1]) {
                        isSuccessValid = true;
                    }
                    if (isSuccessValid) {
                        session.setAttribute(loginKey, newLogin);
                        session.setAttribute(passwordKey, newPassword);
                        session.setAttribute(isSuccessValidKey, true);
                    } else {
                        session.setAttribute(isSuccessValidKey, false);
                    }
                    out.println(new MyRequest(Accounts.isValidData(new Profile(newLogin, newPassword)), newLogin).
                            getOut());
                }
            }
        }
        out.close();
    }

}
