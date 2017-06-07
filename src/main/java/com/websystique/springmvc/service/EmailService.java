package com.websystique.springmvc.service;

import com.websystique.springmvc.model.ProfileEnum;
import com.websystique.springmvc.model.University;
import com.websystique.springmvc.model.User;
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
@PropertySource(value = {"classpath:email.properties"})
@Service
public class EmailService {

    @Autowired
    private Session emailSession;

    @Autowired
    private UserService userService;

    private static final String emailMessage = "%s invited you to join %s UniHub Community as %s. Please follow this link to complete your registration: %s";

    private static final String emailSubject = "Create UniHub account";

    private static final String incomingLink = "http://unihub.com:8081/create-account/%s";

    /**
     * Sends the automatically email
     * @param receivers recipients emails
     * @param userName username which invited the recipients
     * @param profileEnum the selected profile for invited people
     * @throws MessagingException
     */
    public void sendEmailInvitation(String receivers, String userName, ProfileEnum profileEnum) throws MessagingException {
        Message message = new MimeMessage(emailSession);

        message.setFrom(new InternetAddress(emailSession.getProperty("mail.fromAddress")));
        message.setSubject(emailSubject);
        for (String emailToBeInvited : receivers.trim().replaceAll("\\s+", " ").split(" ")) {
            buildMessageAndSendEmail(message, emailToBeInvited, userName, profileEnum);
        }
    }

    /**
     * Constructs the email message and sends the automatically email.
     * @param message given message containing sender email and the email subject
     * @param address given email address for receiver
     * @param userName which sends the automatically email
     * @param profileEnum selected profile for invited people
     * @throws MessagingException
     */
    private void buildMessageAndSendEmail(Message message, String address, String userName, ProfileEnum profileEnum) throws MessagingException {
        User user = userService.findBySSO(userName);
        University university = user.getUniversity();

        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
        message.setText(String.format(emailMessage, user.getFirstName() + " " + user.getLastName(), university.getLongName(), profileEnum.getUserProfileType().toLowerCase(), String.format(incomingLink, address.substring(0, address.indexOf("@")))));

        Transport.send(message);
    }
}
