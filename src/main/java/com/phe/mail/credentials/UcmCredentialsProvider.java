package com.phe.mail.credentials;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;

public class UcmCredentialsProvider implements AWSCredentialsProvider {





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
