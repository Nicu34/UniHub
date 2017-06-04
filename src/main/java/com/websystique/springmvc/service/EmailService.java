package com.websystique.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by nicu on 6/3/2017.
 */
@PropertySource(value = { "classpath:email.properties" })
@Service
public class EmailService {

    @Autowired
    private Session emailSession;

    public void sendEmail(String toAddress) {
        try {
            Message message = new MimeMessage(emailSession);

            message.setFrom(new InternetAddress(emailSession.getProperty("mail.fromAddress")));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            message.setSubject("HELLO THERE");
            message.setText("THIS IS MASTER OBI WAN KENOBI");

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
