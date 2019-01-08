package com.netcracker.students.BatyrkinAndrew.servlets.task2;

import com.netcracker.students.BatyrkinAndrew.help.Accounts;
import com.netcracker.students.BatyrkinAndrew.help.MyCookies;
import com.netcracker.students.BatyrkinAndrew.help.MyRequest;
import com.netcracker.students.BatyrkinAndrew.help.pojo.Profile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/tasks/task2/cookie")
public class ReadCookies extends HttpServlet {

    @SuppressWarnings("Duplicates")
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Accounts.init(getServletContext().getRealPath("/WEB-INF/classes/users.txt"));
        MyCookies myCookies;

        String login = "";
        String password = "";
        boolean isSV = false;
        boolean[] isValidData = new boolean[2];

        String loginKey = "Login";
        String passwordKey = "Password";
        String isSuccessValidKey = "isSuccessValid";

        String newLogin = "";
        String newPassword = "";

        Cookie cLogin = new Cookie(loginKey, login);
        Cookie cPassword = new Cookie(passwordKey, password);
        Cookie cIsSuccessValid = new Cookie(isSuccessValidKey, isSuccessValid(isSV));

        login = req.getParameter("login");
        password = req.getParameter("password");

        if (req.getCookies() != null && req.getCookies().length >= 3) {
            myCookies = new MyCookies(req.getCookies());

            if (login.length() != 0/* && password.length() != 0*/) {
                if (!Accounts.isValidLogin(login)) {
                    response.sendRedirect("/tasks/task2/create_account.html");
//                    out.println("Incorrect Login!" + "<br>");
                } else {
                    isValidData = Accounts.isValidData(new Profile(login, password));
                    if (isValidData[0] && isValidData[1]) {
                        isSV = true;
                    }
                    if (isSV) {
                        cIsSuccessValid.setValue(isSuccessValid(true));
                        cLogin.setValue(login);
                        cPassword.setValue(password);
                    } else {
                        cIsSuccessValid.setValue(isSuccessValid(false));
                    }
                    out.println(new MyRequest(Accounts.isValidData(new Profile(login, password)), login).getOut());
                }
            } else {
//                login = req.getCookies()[0].getName().equals(loginKey) ? req.getCookies()[0].getValue() : "";
//                password = req.getCookies()[1].getName().equals(passwordKey) ? req.getCookies()[1].getValue() : "";
                login = myCookies.getLogin();
                password = myCookies.getPassword();
//            password = req.getCookies()[1].getValue();
                if (myCookies.isSuccessValid()) {
                    out.print(new MyRequest(Accounts.isValidData(new Profile(login, password)), login).getOut() + "<br>");
                    cIsSuccessValid.setValue("true");
                } else {
                    out.print("Shit!");
                }
                cLogin.setValue(login);
                cPassword.setValue(password);
            }
        } else {
            out.println("Incorrect Data!" + "<br>");
        }

        response.addCookie(cLogin);
        response.addCookie(cPassword);
        response.addCookie(cIsSuccessValid);

        MyCookies.writeCookies(out, req.getCookies());

        out.close();
    }


    private String isSuccessValid(boolean isSV) {
        return isSV ? "true" : "false";
    }

    private boolean isSuccessValid(String iSV) {
        return iSV.equals("true");
    }




}
