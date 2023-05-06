package com.itz.gst.use_cases.aaa_module.auth;

import org.springframework.mail.SimpleMailMessage;

public class AuthModel {


    public SimpleMailMessage requestSendToMail(String fromEmail, String toEmail, String token, String sub, String msg) throws Exception {
        try {
            SimpleMailMessage mailMsg = new SimpleMailMessage();
            mailMsg.setFrom(fromEmail);
            mailMsg.setTo(toEmail);
            mailMsg.setSubject(sub);
            mailMsg.setText(msg + " " + token + " It will be expired in few minutes");

            return mailMsg;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

}
