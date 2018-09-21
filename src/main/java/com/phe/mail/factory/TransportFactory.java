package com.phe.mail.factory;

import javax.mail.NoSuchProviderException;
import javax.mail.Transport;

public interface TransportFactory {

    public Transport getTransport() throws NoSuchProviderException;
}
