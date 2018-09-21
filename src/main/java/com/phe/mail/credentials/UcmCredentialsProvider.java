package com.phe.mail.credentials;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;

public class UcmCredentialsProvider implements AWSCredentialsProvider {

    private final static String accessKeyId = "AKIAIV7TN27E3UW4ZLJA";
    private final static String secretAccessKey = "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";



    private UcmCredentials credentials;

    public UcmCredentialsProvider() {
        this.credentials = new UcmCredentials()
                .withAccessKeyId(accessKeyId)
                .withSecretKey(secretAccessKey);
    }

    @Override
    public AWSCredentials getCredentials() {
        return credentials;
    }

    @Override
    public void refresh() {
        throw new UnsupportedOperationException("Not implemented for ths function");
    }
}
