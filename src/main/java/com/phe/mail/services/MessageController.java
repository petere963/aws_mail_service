package com.phe.mail.services;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.phe.mail.configuration.ServiceConfiguration;
import org.apache.log4j.Logger;

import java.util.List;

public class MessageController implements Runnable {

    private final S3FileService s3FileService;
    private final MailProcessor mailProcessor;
    private final ServiceConfiguration configuration;

    private static final Logger LOGGER = Logger.getLogger(MessageController.class);

    public MessageController(S3FileService s3FileService,
                             MailProcessor mailProcessor,
                             ServiceConfiguration configuration) {

        this.s3FileService = s3FileService;
        this.mailProcessor = mailProcessor;
        this.configuration = configuration;
    }

    @Override
    public void run() {

        while(true) {
            executeProcess();
            sleep();
        }
    }

    public void executeProcess() {

        LOGGER.info("Getting files from S3 ....");
        List<S3ObjectSummary> files = s3FileService.getEmails();
        LOGGER.info("Found " + files.size() + " files in S3 ....");

        files.stream().forEach(this :: processEmail);
    }

    private void processEmail(S3ObjectSummary s3ObjectSummary) {

        LOGGER.info("Processing file " + s3ObjectSummary);

        String email = s3FileService.extractEmail(s3ObjectSummary);
        mailProcessor.forwardEmail(email);
        s3FileService.deleteFile(s3ObjectSummary);
    }

    private void sleep() {
        try {
            LOGGER.info("Sleeping for " + configuration.getSleepMillis());
            Thread.sleep(configuration.getSleepMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
