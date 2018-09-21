package com.phe.mail.services;

import org.apache.log4j.Logger;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Properties;

public class EmailDecoder {

    private MimeMessage message;
    private static final Logger LOGGER = Logger.getLogger(EmailDecoder.class);

    public EmailDecoder(String emailString) {
        try {
            message = decode(emailString);
        } catch (MessagingException e) {
            LOGGER.error(e);
        }
    }

    public MimeMessage getMessage() {
        return message;
    }

    public String getSubject() {
        try {
            return message.getSubject();
        } catch (MessagingException e) {
            LOGGER.error(e);
            return "Unable to decode subject from email";
        }
    }

    private MimeMessage decode(String emailString) throws MessagingException {

        Properties props = System.getProperties();
        props.put("mail.host", "smtp.dummydomain.com");
        props.put("mail.transport.protocol", "smtp");

        Session mailSession = Session.getDefaultInstance(props, null);
        InputStream source = new ByteArrayInputStream(emailString.getBytes());
        return new MimeMessage(mailSession, source);
    }

    public int getAddressCount() {
        try {
            return message.getFrom().length;
        } catch (MessagingException e) {
            LOGGER.error(e);
            return -1;
        }
    }

    public String getAddress(int addressIndex) {
        try {
            Address[] addresses = message.getFrom();
            return addresses[addressIndex].toString();
        } catch (MessagingException e) {
            LOGGER.error(e);
            return "Unable to get address number " + addressIndex;
        }
    }

    public String getContent() {
        try {
            return message.getContent().toString();
        } catch (Exception e) {
            LOGGER.error(e);
            return "Unable to get Content for Message";
        }

    }
}
