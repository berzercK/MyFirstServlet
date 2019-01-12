package com.netcracker.students.BatyrkinAndrew.servlets.task2;

import com.netcracker.students.BatyrkinAndrew.help.Accounts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/tasks/task2/change_password")
public class ChangePassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        StringBuilder sb = new StringBuilder();
        HttpSession session = request.getSession(true);

        String login = request.getParameter("login");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        if (Accounts.isValidLogin(login)) {
            sb.append(checkPassword(password1, password2) ? "" : "Passwords do not match!");
            if (Accounts.changePassword(login, password1)) {
                sb.append("Successfully!");
            } else {
                sb.append("You new password is the same as the old one!");
            }
        } else {
            sb.append("Account with this login is not exist!"); // почти не достижимо
        }

        out.print(sb);

        out.close();
    }


    private boolean checkPassword(String password1, String password2) {
        return password1.equals(password2);
    }


}
