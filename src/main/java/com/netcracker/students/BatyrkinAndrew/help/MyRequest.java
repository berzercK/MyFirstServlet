package com.netcracker.students.BatyrkinAndrew.help;

public class MyRequest {
    private StringBuilder out;

    public MyRequest(boolean[] isValidData) {
        out = new StringBuilder();
        out = checkData(isValidData);
    }

    private StringBuilder checkData(boolean[] isValidData) {
        if (!isValidData[0]) {
            out.append("Incorect login!");
        } else if (!isValidData[1]) {
            out.append("Incorrect password!");
        } else {
            out.append("Successfully");
        }
        return out;
    }

    public StringBuilder getOut() {
        return out;
    }
}
