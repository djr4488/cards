package com.djr.cards.email;

import javax.mail.Session;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/13/14
 * Time: 10:42 PM
 */
public interface EmailService {
    public boolean sendEmail(String recipient, String name, String subject, String body);
}
