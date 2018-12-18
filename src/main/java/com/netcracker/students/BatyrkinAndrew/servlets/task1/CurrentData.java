package com.netcracker.students.BatyrkinAndrew.servlets.task1;

import com.netcracker.students.BatyrkinAndrew.help.Info;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/tasks/task1/data_and_user-agent")
public class CurrentData extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
//
//        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String title = "DATATIME";
        String docType = "<!DOCTYPE html> \n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<h3 align=\"center\">" + Info.getInfo(request) + "</h2>\n" +
                "<h3 align=\"center\">" + Info.getDataTime() + "</h2>\n" +
                "<h4 align=\"center\">" + "UserAgent is:   " + Info.getBrowser(request) + "</h2>\n" +
                "</body></html>");

        out.close();
    }

}
