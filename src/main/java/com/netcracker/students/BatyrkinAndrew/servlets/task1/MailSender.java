package com.netcracker.students.BatyrkinAndrew.servlets.task1;

import com.netcracker.students.BatyrkinAndrew.help.Mailer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/tasks/task1/mail_sender")
public class MailSender extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter out = resp.getWriter();

        String reciever = req.getParameter("reciever");
        String cc = req.getParameter("cc");
        String subject = req.getParameter("subject");
        String message = req.getParameter("message");

        req.setAttribute("reciever", "andrey.batyrkin@gmail.com");

        Mailer.send(reciever, cc, subject, message);

        out.print("Message send successfully!");
        out.close();
    }
}
