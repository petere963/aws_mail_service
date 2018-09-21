package com.phe.mail.services;

import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.util.List;

public interface S3FileService {

    List<S3ObjectSummary> getEmails();

    String extractEmail(S3ObjectSummary s3ObjectSummary);

    void deleteFile( S3ObjectSummary s3ObjectSummary);
}
