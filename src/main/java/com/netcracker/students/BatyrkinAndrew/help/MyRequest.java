package com.netcracker.students.BatyrkinAndrew.help;

public class MyRequest {
    private StringBuilder out;

    public MyRequest(boolean[] isValidData, String login) {
        out = new StringBuilder();
        out = checkData(isValidData, login);
    }

    private StringBuilder checkData(boolean[] isValidData, String login) {
        if (!isValidData[0]) {
            out.append("Incorect login!");
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
