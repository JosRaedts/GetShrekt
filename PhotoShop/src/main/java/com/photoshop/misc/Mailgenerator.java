/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.misc;

import com.photoshop.models.order.Order;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author bart
 */
public class Mailgenerator {

    private Order order;
    
    public void Sendmail(String OntvangerEmail,Order order) {
        this.order = order;
        final String username = "fotowinkelpts42@gmail.com";
        final String password = "Fotoshop";

        Properties props = new Properties();

        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("fotowinkelpts42@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(OntvangerEmail));
            message.setSubject("Factuur" + order.getId());
            message.setText("Dear Willem de kok,"
                    + "\n\n No spam to my email, please!");
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);

        }
    }

}
