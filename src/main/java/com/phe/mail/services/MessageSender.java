package com.phe.mail.services;

import com.phe.mail.configuration.ServiceConfiguration;
import com.phe.mail.factory.TransportFactory;
import org.apache.log4j.Logger;

import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class MessageSender {

    private final TransportFactory transportFactory;
    private final ServiceConfiguration configuration;
    private static final Logger LOGGER = Logger.getLogger(MessageSender.class);

    public MessageSender(TransportFactory transportFactory, ServiceConfiguration configuration) {
        this.transportFactory = transportFactory;
        this.configuration = configuration;
    }

    public void send(MimeMessage msg) throws Exception {
        Transport transport = transportFactory.getTransport();
        sendMessage(transport, msg);
        transport.close();
    }

    private void sendMessage(Transport transport, MimeMessage msg)  {

        try
        {
            transport.connect(configuration.getSmtpHost(), configuration.getSmtpUserName(), configuration.getSmtpPassword());
            transport.sendMessage(msg, msg.getAllRecipients());
        }
        catch (Exception ex) {
            LOGGER.info("The email was not sent.");
            LOGGER.info("Error message: " + ex.getMessage());
        }
    }
}
