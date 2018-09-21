package com.phe.mail;

import com.phe.mail.configuration.ServiceConfiguration;
import com.phe.mail.entity.MemberDetailsList;
import com.phe.mail.factory.TransportFactory;
import com.phe.mail.factory.TransportFactoryImpl;
import com.phe.mail.services.*;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.apache.log4j.Logger;


public class MailServer extends Application<ServiceConfiguration> {

    public static final String OBB = "obb";
    public static final String WHARFE = "wharfe";
    public static final String STEPS = "steps";

    public static final String OTLEYBRASSBAND = "otleybrassband";
    private static final Logger LOGGER = Logger.getLogger(MailServer.class);

    public static void main(String[] args) throws Exception {
        new MailServer().run(args);
    }

    @Override
    public void run(ServiceConfiguration configuration, Environment environment) throws Exception {

        System.setProperty("aws.csmEnabled", configuration.getAwsCsmEnabled());
        System.setProperty("aws.region", configuration.getAwsRegion());
        System.setProperty("aws.accessKeyId", configuration.getAwsAccessKeyId());
        System.setProperty("aws.secretKey", configuration.getAwsSecretKey());

        TransportFactory transportFactory = new TransportFactoryImpl();

        S3FileService s3FileService = new S3FileServiceImpl(OTLEYBRASSBAND);
        MailProcessor mailProcessor = new MailProcessorImpl(transportFactory, getMemberDetails(OBB), configuration);
        MessageController controller = new MessageController(s3FileService, mailProcessor, configuration);

        new Thread(controller).start();
    }

    private MemberDetailsList getMemberDetails(String bandId) {
        try {
            return new PersistenceService().getDetailsFromFile(bandId);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new RuntimeException("Unable to load member details");
        }
    }

}
