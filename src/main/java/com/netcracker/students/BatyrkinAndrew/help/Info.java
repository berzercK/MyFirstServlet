package com.netcracker.students.BatyrkinAndrew.help;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class Info {

    public static String getBrowser(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    public static String getDataTime() {
        return new Date().toString();
    }


    public static String getInfo(HttpServletRequest request) {
        String browserDetails = request.getHeader("User-Agent");
        String user = browserDetails.toLowerCase();

        String os = "";
        String browser = "";

        //=================OS=======================
        if (browserDetails.toLowerCase().contains("windows")) {
            os = "Windows";
        } else if (browserDetails.toLowerCase().contains("mac")) {
            os = "Mac";
        } else if (browserDetails.toLowerCase().contains("x11")) {
            os = "Unix";
        } else if (browserDetails.toLowerCase().contains("android")) {
            os = "Android";
        } else if (browserDetails.toLowerCase().contains("iphone")) {
            os = "IPhone";
        } else {
            os = "UnKnown, More-Info: " + browserDetails;
        }
        //===============Browser===========================
        if (user.contains("msie")) {
            String substring = browserDetails.substring(browserDetails.indexOf("MSIE")).split(";")[0];
            browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
        } else if (user.contains("safari") && user.contains("version")) {
            browser = (browserDetails.substring(browserDetails.indexOf("Safari")).split(" ")[0]).
                    split("/")[0] + "-" + (browserDetails.substring(browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
        } else if (user.contains("opr") || user.contains("opera")) {
            if (user.contains("opera"))
                browser = (browserDetails.substring(browserDetails.indexOf("Opera")).split(" ")[0]).
                        split("/")[0] + "-" + (browserDetails.substring(browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
            else if (user.contains("opr"))
                browser = ((browserDetails.substring(browserDetails.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
        } else if (user.contains("yabrowser")) {
            browser = (browserDetails.substring(browserDetails.indexOf("YaBrowser")).split(" ")[0]).replace("/", "-");
        } else if (user.contains("chrome")) {
            browser = (browserDetails.substring(browserDetails.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
        } else if ((user.contains("mozilla/7.0")) || (user.contains("netscape6")) || (user.contains("mozilla/4.7")) ||
                (user.contains("mozilla/4.78")) || (user.contains("mozilla/4.08")) || (user.contains("mozilla/3"))) {
            //browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
            browser = "Netscape-?";

        } else if (user.contains("firefox")) {
            browser = (browserDetails.substring(browserDetails.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
        } else if (user.contains("rv")) {
            browser = "IE-" + user.substring(user.indexOf("rv") + 3, user.indexOf(")"));
        } else {
            browser = "UnKnown, More-Info: " + browserDetails;
        }

        return "Current OS: " + os + ", browser: " + browser;

    }
}
