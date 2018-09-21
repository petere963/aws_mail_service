package com.phe.mail.services;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class S3FileReader {

    //public static final String OTLEYBRASSBAND = "otleybrassband";
    private List<S3ObjectSummary> files;
    private final AmazonS3 s3Client;
    private final String bucketName;
    private static final Logger LOGGER = Logger.getLogger(S3FileReader.class);

    public S3FileReader(String bucketName) {
        this.bucketName = bucketName;
        files = new ArrayList<>();
        s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    public  List<S3ObjectSummary> read() throws IOException {

        ListObjectsV2Result objectsInBucket = s3Client.listObjectsV2(bucketName);

        objectsInBucket
                .getObjectSummaries()
                .forEach(this :: loadFile);

        LOGGER.info("Got " + files.size() + " email files ...");
        return files;
    }

    public S3Object getObject(S3ObjectSummary s3ObjectSummary) {
        LOGGER.info("Processing email : " + s3ObjectSummary);

        ResponseHeaderOverrides headerOverrides = new ResponseHeaderOverrides()
                .withCacheControl("No-cache")
                .withContentDisposition("attachment; filename=example.txt");
        GetObjectRequest getObjectRequestHeaderOverride = new GetObjectRequest(bucketName, s3ObjectSummary.getKey())
                .withResponseHeaders(headerOverrides);
        S3Object s3Object = s3Client.getObject(getObjectRequestHeaderOverride);
        return s3Object;
    }

    public void deleteFile(S3ObjectSummary s3ObjectSummary) {
        s3Client.deleteObject(new DeleteObjectRequest(bucketName, s3ObjectSummary.getKey()));
    }

    private void loadFile(S3ObjectSummary s3ObjectSummary) {
        if(isEmailFile(s3ObjectSummary.getKey())) {
            files.add(s3ObjectSummary);
        } else {
            LOGGER.error("Non email file detected " + s3ObjectSummary);
        }
    }

    private boolean isEmailFile(String key) {
        return (key.length() > 5) && (key.substring(0, 5).equalsIgnoreCase("Email"));
    }
}