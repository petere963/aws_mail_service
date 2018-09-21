package com.phe.mail.services;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by peter.earle on 21/09/2018.
 */
public class S3FileServiceImpl implements S3FileService {

    private static final Logger LOGGER = Logger.getLogger(S3FileServiceImpl.class);

    private final String bucketName;

    public S3FileServiceImpl(String bucketName) {
        this.bucketName = bucketName;
    }

    @Override
    public List<S3ObjectSummary> getEmails() {
        try {
            return new S3FileReader(bucketName).read();
        } catch (IOException e) {
            LOGGER.error(e);
            return  new ArrayList<>();
        }
    }

    @Override
    public String extractEmail(S3ObjectSummary s3ObjectSummary) {
        S3Object s3Object = new S3FileReader(bucketName).getObject(s3ObjectSummary);
        try {
            return displayTextInputStream(s3Object.getObjectContent());
        } catch (IOException e) {
            LOGGER.error(e);
            return null;
        }
    }

    @Override
    public void deleteFile(S3ObjectSummary s3ObjectSummary) {

        new S3FileReader(bucketName).deleteFile(s3ObjectSummary);
    }

    private String displayTextInputStream(InputStream input) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        return sb.toString();
    }
}
