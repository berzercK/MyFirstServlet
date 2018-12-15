package com.netcracker.students.BatyrkinAndrew.help;

import com.netcracker.students.BatyrkinAndrew.help.pojo.Profile;

import java.util.ArrayList;
import java.util.List;

public class Accounts {

    private static List<Profile> justAccounts = new ArrayList<>();

    static {
        justAccounts.add(new Profile("Andrew", "Batyrkin"));
        justAccounts.add(new Profile("1234", "0987"));
    }

/*    public static List<Profile> getAccounts() {
        return justAccounts;
    }
*/
/*    public static void setAccounts(List<Profile> justAccounts) {
        if (Accounts.justAccounts.size() != 0) Accounts.justAccounts.clear();
        Accounts.justAccounts.add((Profile) justAccounts);
    }
*/
    /*-------------------------------------Check login and password-----------------------------------------------*/


    public static boolean[] isValidData(Profile profile) {
        /*
         * Первый элемент массива показывает правильность ввода логина
         * Второй элемент массива показывает правильность ввода пароля
         * Анализ этого массива происходит в классе MyRequest
         * */

        boolean[] isValidData = new boolean[2];

        isValidData[0] = isValidLogin(profile);
        if (isValidLogin(profile)) {
            isValidData[1] = isValidPassword(profile);
        }

        return isValidData;
    }

    private static boolean isValidLogin(Profile profile) {
        for (Profile p :
                justAccounts) {
            if (p.getLogin().equals(profile.getLogin())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValidPassword(Profile profile) {
        for (Profile p :
                justAccounts) {
            if (p.getPassword().equals(profile.getPassword())) {
                return true;
            }
        }
        return false;
    }

    /*-------------------------------------Add Profile-----------------------------------------------*/

    public static boolean addProfile(Profile profile) {
        if (isNewLogin(profile.getLogin())) {
            justAccounts.add(profile);
            return true;
        } else {
            return false;
        }
    }

    private static boolean isNewLogin(String login) {
        for (Profile profile :
                justAccounts) {
            if (profile.getLogin().equals(login)) {
                return false;
            }
        }
        return true;
    }

    /*-------------------------------------------------------------------------------------------*/
}
