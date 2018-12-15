package com.netcracker.students.BatyrkinAndrew.servlet;

import com.netcracker.students.BatyrkinAndrew.help.Accounts;
import com.netcracker.students.BatyrkinAndrew.help.MyRequest;
import com.netcracker.students.BatyrkinAndrew.help.pojo.Profile;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/tasks/task1/login_servlet")
public class Login extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String login = request.getParameter("login");
        String password = request.getParameter("password");

//        statusOut(out, login, password);
        out.println(new MyRequest(Accounts.isValidData(new Profile(login, password))).getOut());

        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);

    }

    private void statusOut(PrintWriter writer, String login, String password) {
        writer.println("Hello, " + login + "!");
        writer.println("Your password \"" + password + "\" is so funny :D");
    }

}
