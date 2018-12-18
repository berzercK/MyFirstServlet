package com.netcracker.students.BatyrkinAndrew.help;

import com.netcracker.students.BatyrkinAndrew.help.pojo.Profile;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Accounts {
    private static boolean isInit;
    private static String path;
    private static List<Profile> justAccounts = new ArrayList<>();
    private static int lastIndexAccFromFile = 0;

    public static void init(String ss) {
        path = ss;
        if (!isInit) {
            isInit = true;
            try {
                FileReader fileReader = new FileReader(path);

                System.out.println("File is read.");
                Scanner sc = new Scanner(fileReader);

                List<String> usersList = new ArrayList<>();
                while (sc.hasNextLine()) {
                    usersList.add(sc.nextLine());
                    lastIndexAccFromFile++;
                }
                for (String s : usersList) {
                    justAccounts.add(new Profile(s.substring(0, s.indexOf(',')),
                            s.substring(s.indexOf(',') + 1)));
                }
                fileReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

/*    static {
        justAccounts.add(new Profile("Andrew", "Batyrkin"));
        justAccounts.add(new Profile("1234", "0987"));
        justAccounts.add(new Profile("333", "333"));

    }*/

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

    public static boolean isValidLogin(String login) {
        for (Profile p :
                justAccounts) {
            if (p.getLogin().equals(login)) {
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
            writeToFile();
            return true;
        } else {
            return false;
        }
    }

    private static boolean writeToFile() {
        try {
            FileWriter fileWriter = new FileWriter(path, true);
            for (int i = lastIndexAccFromFile; i < justAccounts.size(); i++) {
                fileWriter.write(String.valueOf(justAccounts.get(lastIndexAccFromFile).getLogin()));
                fileWriter.write(',');
                fileWriter.write(String.valueOf(justAccounts.get(lastIndexAccFromFile).getPassword()));
                fileWriter.write('\n');
            }
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
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
