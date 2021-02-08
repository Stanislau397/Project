package edu.epam.project.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class MailSender {

    private static final Logger logger = LogManager.getLogger(MailSender.class);
    private static final String MAIL_PROPERTIES = "config/mail";
    private static final String USER_NAME_KEY = "mail.user.name";
    private static final String PASSWORD_KEY = "mail.user.password";
    private static final boolean CONDITION = true;
    private MimeMessage message;

    public void sendEmail(String email, String subject, String body) {
        Session mailSession = createSession();
        mailSession.setDebug(CONDITION);
        message = new MimeMessage(mailSession);
        try {
            message.setSubject(subject);
            message.setText(body);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            Transport.send(message);
        } catch (AddressException e) {
            logger.log(Level.ERROR, e.getMessage());
        } catch (MessagingException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }

    public Session createSession() {
        Properties properties = new Properties();
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            properties.load(classLoader.getResourceAsStream(MAIL_PROPERTIES));
        } catch (IOException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        String emailFrom = properties.getProperty(USER_NAME_KEY);
        String password = properties.getProperty(PASSWORD_KEY);
        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailFrom, password);
            }
        });
    }
}
