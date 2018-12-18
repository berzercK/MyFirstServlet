package com.netcracker.students.BatyrkinAndrew.servlets.task2;

import com.netcracker.students.BatyrkinAndrew.help.Accounts;
import com.netcracker.students.BatyrkinAndrew.help.pojo.Profile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/tasks/task2/create_account")
public class CreateAccount extends HttpServlet {

    @SuppressWarnings("Duplicates")
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = null;
        try { out = resp.getWriter(); }
        catch (IOException e) { e.printStackTrace(); }

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (!Accounts.isValidLogin(login)) {
        Accounts.addProfile(new Profile(login, password));
            out.println("Account created");
        } else { out.print("Account with this login is already exist!"); }

        out.close();
    }
}
