package com.phe.mail.credentials;

import com.amazonaws.auth.AWSCredentials;

/**
 * Created by peter.earle on 21/02/2018.
 */
public class UcmCredentials implements AWSCredentials {
    private String accessKeyId;
    private String secretKey;

    @Override
    public String getAWSAccessKeyId() {
        return accessKeyId;
    }

    @Override
    public String getAWSSecretKey() {
        return secretKey;
    }

    public UcmCredentials withAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
        return this;
    }

    public UcmCredentials withSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }
}
