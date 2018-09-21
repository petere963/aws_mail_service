package com.phe.mail.services;

import com.phe.mail.configuration.ServiceConfiguration;
import com.phe.mail.entity.MemberDetails;
import com.phe.mail.entity.MemberDetailsList;
import com.phe.mail.factory.TransportFactory;
import org.apache.log4j.Logger;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailProcessorImpl implements MailProcessor {

    private final TransportFactory transportFactory;
    private static final Logger LOGGER = Logger.getLogger(MailProcessorImpl.class);
    private final MemberDetailsList memberDetailsList;
    private final ServiceConfiguration configuration;

    public MailProcessorImpl(TransportFactory transportFactory,
                             MemberDetailsList memberDetailsList,
                             ServiceConfiguration configuration) {
        this.transportFactory = transportFactory;
        this.memberDetailsList = memberDetailsList;
        this.configuration = configuration;
    }

    @Override
    public void forwardEmail(String email) {
        MimeMessage mimeMessage = new EmailDecoder(email).getMessage();

        LOGGER.info("forwarding following email to all OBB");
        try {
            LOGGER.info(mimeMessage.getSubject());
            LOGGER.info(mimeMessage.getContent());
        } catch (Exception e) {
            LOGGER.error("Invalid Message " + email);
            return;
        }

        memberDetailsList.getMemberDetails().stream().forEach(member -> forwardEmailToMember(member, mimeMessage));
    }

    private void forwardEmailToMember(MemberDetails memberDetails, MimeMessage mimeMessage) {
        try {
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(memberDetails.getEmail()));
            new MessageSender(transportFactory, configuration).send(mimeMessage);
        } catch (Exception e) {
            LOGGER.error(e);
            LOGGER.error("Unable to process email " + mimeMessage);
        }

    }
}
