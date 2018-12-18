package com.netcracker.students.BatyrkinAndrew.help;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mailer {

    public static void send(String reciever, String cc, String subject, String message) {

        final String user = "myemail.ForSmth.tests@gmail.com";
        final String pass = "itisjustMYpassword";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, pass);
                    }
                });
//      compose message
        try {
            MimeMessage mess = new MimeMessage(session);
            mess.setFrom(new InternetAddress(user));
            mess.addRecipient(Message.RecipientType.TO, new InternetAddress(reciever));
            if (!cc.equals("")) {
                mess.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
            }
            mess.setSubject(subject);
            mess.setText(message);

//          send message
            Transport.send(mess);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}

//        final String user = "servlets.fake@mail.ru";
//        final String pass = "1234567p";
//
//        Properties props = new Properties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.host", "smtp.mail.ru");
//        props.put("mail.smtp.auth", "true");
////        props.put("mail.smtp.startls.enable", "true");
////        props.put("mail.smtp.host", "smtp.mail.ru");
//        props.put("mail.smtp.port", "465");
//
////        Session session = Session.getDefaultInstance(props,
////                new javax.mail.Authenticator() {
////                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
////                        return new PasswordAuthentication(user,pass);
////                    }
////                });
//
////        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
////            protected PasswordAuthentication getPasswordAuthentication() {
////                return new PasswordAuthentication(user, pass);
////            }
////        });
//        Session session = Session.getDefaultInstance(props);
//
//        try {
//            Transport transport = transport = session.getTransport();
//            transport.connect("smtp.mail.ru", 465, user, pass);
//        } catch (NoSuchProviderException e) {
//            e.printStackTrace();
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            if (!reciever.equals("")) {
//                MimeMessage mimeMessage = new MimeMessage(session);
//                mimeMessage.setFrom(new InternetAddress(user));
//
//                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(reciever));
//
//                if (!cc.equals("")) {
//                    mimeMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
//                }
//                mimeMessage.setSubject(subject);
//                mimeMessage.setText(message);
//                mimeMessage.setSentDate(new Date());
//
//                Transport.send(mimeMessage);
//            }
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
