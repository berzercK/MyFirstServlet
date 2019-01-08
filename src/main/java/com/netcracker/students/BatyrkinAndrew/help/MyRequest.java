package com.netcracker.students.BatyrkinAndrew.help;

public class MyRequest {
    private StringBuilder out;

    public MyRequest(boolean[] isValidData, String login) {
        out = new StringBuilder();
        out = checkData(isValidData, login);
    }

    public MyRequest(boolean isSuccessValid, String login) {
        out = new StringBuilder();
        if (isSuccessValid) { out.append("Successfully").append("<br> Welcome, ").append(login); }
//        } else { out.append("Incorrect"); }
    }

    private StringBuilder checkData(boolean[] isValidData, String login) {
        if (!isValidData[0]) {
            out.append("Incorrect login!");
        } else if (!isValidData[1]) {
            out.append("Incorrect password!");
        } else {
            out.append("Successfully").append("\nWelcome, ").append(login);
        }
        return out;
    }

    public StringBuilder getOut() {
        return out;
    }
}
