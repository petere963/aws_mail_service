package com.phe.mail.factory;

import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import java.util.Properties;

/**
 * Created by peter.earle on 20/09/2018.
 */
public class TransportFactoryImpl implements TransportFactory {

    static final String SMTP_USERNAME = "AKIARSF67VUKKRW4PHUK";
    static final String SMTP_PASSWORD = "AoptPyY+izr/MzjHekeRM7DwDkbDiio9T4qYWEGtkuAA";
    static final String HOST = "email-smtp.us-east-1.amazonaws.com";
    static final int PORT = 587;

    private  Session session;

    public TransportFactoryImpl() {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        session = Session.getDefaultInstance(props);
    }

    @Override
    public Transport getTransport() throws NoSuchProviderException {
        return session.getTransport();
    }
}
