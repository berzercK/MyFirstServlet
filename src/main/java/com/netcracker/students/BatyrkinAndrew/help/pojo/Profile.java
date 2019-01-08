package com.netcracker.students.BatyrkinAndrew.help.pojo;

import java.util.Objects;

public class Profile {
    private String login;
    private String password;

    public Profile(String login, String password) {
        if (!login.equals("") || !password.equals("")) {
            this.login = login;
            this.password = password;
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        //Check is Valid while add new profile
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        //Check is Valid while reg new profile
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return login.equals(profile.login) &&
                password.equals(profile.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}
