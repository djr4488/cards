package com.djr.cards.email;

import org.slf4j.Logger;
import javax.annotation.Resource;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/13/14
 * Time: 10:45 PM
 */
public class EmailServiceImpl implements EmailService {
    @Inject
    private Logger logger;
    @Inject
    private String host;
    @Inject
    private String port;
    @Inject
    private String username;
    @Inject
    private String password;

    @Override
    public boolean sendEmail(String recipient, String name, String subject,
                             String body) {
        logger.debug("sendEmail() - recipient:{}, name:{}, subject:{}, body:{}", recipient,
                name, subject, body);
        try {
            Message message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress("djr4488@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
        } catch (Exception ex) {
            logger.error("sendEmail() - exception:", ex);
            return false;
        }

        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Session getSession() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.timeout", 10000);
        return Session.getInstance(properties,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            }
        );
    }
}
