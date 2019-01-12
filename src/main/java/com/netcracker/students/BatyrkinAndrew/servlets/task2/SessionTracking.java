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

    private final int MAX_ATTEMPT_LOGIN = 3;

    @SuppressWarnings("Duplicates")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(true);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String isRemeber = request.getParameter("remember");

        Accounts.init(getServletContext().getRealPath("/WEB-INF/classes/users.txt"));

        String login = "";
        String password = "";
        boolean isSuccessValid = false;
        boolean[] isValidData = new boolean[2];
        boolean[] isValidDataNewProfile = new boolean[2];
        int attemptLoginAmount = 0;
        String attemptLoginName = "";
        String newLogin = "";
        String newPassword = "";

        String loginKey = "Login";
        String passwordKey = "Password";
        String isSuccessValidKey = "isSuccessValid";
        String attemptLoginAmountKey = "attemptLoginAmount";
        String attemptLoginNameKey = "attemptLoginName";


        if (isRemeber == null) {
            check(out, request.getParameter("login"), request.getParameter("password"));
        } else {
            if (session.isNew()) {
                session.setAttribute(loginKey, login);
                session.setAttribute(passwordKey, password);
                session.setAttribute(isSuccessValidKey, false);

                session.setAttribute(attemptLoginAmountKey, attemptLoginAmount);
                session.setAttribute(attemptLoginNameKey, attemptLoginName);

                login = request.getParameter("login");
                password = request.getParameter("password");
                isValidData = Accounts.isValidData(new Profile(login, password));

                isSuccessValid = isValidData[0] && isValidData[1];

                if (isSuccessValid) {
                    session.setAttribute(loginKey, login);
                    session.setAttribute(passwordKey, password);
                    session.setAttribute(isSuccessValidKey, true);
                    session.setAttribute(attemptLoginAmountKey, 0);
                    session.setAttribute(attemptLoginNameKey, login);

                } else {
                    session.setAttribute(isSuccessValidKey, false);
                }
            } else {
                newLogin = request.getParameter("login");
                newPassword = request.getParameter("password");

                if (session.getAttribute(attemptLoginAmountKey) != null) attemptLoginAmount = (int) session.getAttribute(attemptLoginAmountKey);
                if (session.getAttribute(attemptLoginNameKey) != null) attemptLoginName = (String) session.getAttribute(attemptLoginNameKey);

                if (newLogin.length() == 0 && newPassword.length() == 0) {
                    if (isSuccessValid = (boolean) session.getAttribute(isSuccessValidKey)) {
                        login = (String) session.getAttribute(loginKey);
                        password = (String) session.getAttribute(passwordKey);
                        out.println(new MyRequest(Accounts.isValidData(new Profile(login, password)), login).getOut());
                    }
                } else {
                    if (!Accounts.isValidLogin(newLogin)) {
                        response.sendRedirect("/tasks/task2/create_account.html");

//                        attemptLoginAmount++;
                    } else {
                        isValidDataNewProfile = Accounts.isValidData(new Profile(newLogin, newPassword));

                        isSuccessValid = isValidDataNewProfile[0] && isValidDataNewProfile[1];
                        if (isSuccessValid) {
                            session.setAttribute(loginKey, newLogin);
                            session.setAttribute(passwordKey, newPassword);
                            session.setAttribute(isSuccessValidKey, true);
                            session.setAttribute(attemptLoginAmountKey, 0);
                            session.setAttribute(attemptLoginNameKey, newLogin);
                            attemptLoginName = newLogin;
                        } else {//если логин верный, а пароль нет
                            session.setAttribute(isSuccessValidKey, false);
                            checkTries(newLogin, isValidDataNewProfile, session, attemptLoginAmount, attemptLoginAmountKey, attemptLoginName, attemptLoginNameKey);
                            if (attemptLoginAmount > MAX_ATTEMPT_LOGIN) {
                                session.setAttribute(attemptLoginAmountKey, 0);
                                session.setAttribute(attemptLoginNameKey, newLogin);
                                response.sendRedirect("/tasks/task2/change_password.jsp");
                            }
                        }
                        out.println(new MyRequest(Accounts.isValidData(new Profile(newLogin, newPassword)), newLogin).getOut());
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Login: ").append(login).append("<br>");
        sb.append("Password: ").append(password).append("<br>");
        sb.append("isRemember: ").append(isRemeber).append("<br>");
        sb.append("isSuccessValid: ").append(isSuccessValid).append("<br>");
        sb.append("AttemptLoginAmount: ").append(attemptLoginAmount).append("<br>");
        sb.append("AttemptLoginName: ").append(attemptLoginName).append("<br>");
        sb.append("New Login: ").append(newLogin).append("<br>");
        sb.append("New Password: ").append(newPassword).append("<br>");

        out.println(sb);

        out.close();
    }

    private void checkTries(String newLogin, boolean[] isValidDataNewProfile, HttpSession session, int attemptLoginAmount, String attemptLoginAmountKey, String attemptLoginName, String attemptLoginNameKey) {
//        if (isValidDataNewProfile[0] && !isValidDataNewProfile[1]) {
        if (session.getAttribute(attemptLoginNameKey).equals(newLogin)) {
            session.setAttribute(attemptLoginAmountKey, (int) session.getAttribute(attemptLoginAmountKey) + 1);
            session.setAttribute(attemptLoginNameKey, attemptLoginName);
        } else {
            session.setAttribute(attemptLoginAmountKey, 1);
            session.setAttribute(attemptLoginNameKey, newLogin);
        }
//        }
    }

    private void check(PrintWriter out, String login, String password) {
        Accounts.init(getServletContext().getRealPath("/WEB-INF/classes/users.txt"));
        out.println(new MyRequest(Accounts.isValidData(new Profile(login, password)), login).getOut());
    }

    private void printSessionParametres(HttpSession session) {

    }
}
