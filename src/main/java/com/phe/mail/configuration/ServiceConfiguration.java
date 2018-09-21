package com.phe.mail.configuration;

import io.dropwizard.Configuration;


public class ServiceConfiguration extends Configuration{

    private String awsCsmEnabled;
    private String awsRegion;
    private String awsAccessKeyId;
    private String awsSecretKey;
    private String smtpUserName;
    private String smtpPassword;
    private String smtpHost;
    private long sleepMillis;

    public String getAwsCsmEnabled() {
        return awsCsmEnabled;
    }

    public void setAwsCsmEnabled(String awsCsmEnabled) {
        this.awsCsmEnabled = awsCsmEnabled;
    }

    public String getAwsRegion() {
        return awsRegion;
    }

    public void setAwsRegion(String awsRegion) {
        this.awsRegion = awsRegion;
    }

    public String getAwsAccessKeyId() {
        return awsAccessKeyId;
    }

    public void setAwsAccessKeyId(String awsAccessKeyId) {
        this.awsAccessKeyId = awsAccessKeyId;
    }

    public String getAwsSecretKey() {
        return awsSecretKey;
    }

    public void setAwsSecretKey(String awsSecretKey) {
        this.awsSecretKey = awsSecretKey;
    }

    public String getSmtpUserName() {
        return smtpUserName;
    }

    public void setSmtpUserName(String smtpUserName) {
        this.smtpUserName = smtpUserName;
    }

    public String getSmtpPassword() {
        return smtpPassword;
    }

    public void setSmtpPassword(String smtpPassword) {
        this.smtpPassword = smtpPassword;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public long getSleepMillis() {
        return sleepMillis;
    }

    public void setSleepMillis(long sleepMillis) {
        this.sleepMillis = sleepMillis;
    }
}
