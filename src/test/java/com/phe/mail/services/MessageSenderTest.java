package com.phe.mail.services;

import com.phe.mail.configuration.ServiceConfiguration;
import com.phe.mail.factory.TransportFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MessageSenderTest {

    private MessageSender messageSender;

    @Mock
    TransportFactory sessionFactory;

    @Mock
    MimeMessage message;

    @Mock
    Transport transport;

    @Mock
    ServiceConfiguration configuration;

    @Before
    public void setUp() throws NoSuchProviderException {
        when(sessionFactory.getTransport()).thenReturn(transport);
       messageSender = new MessageSender(sessionFactory, configuration);
    }

    @Test
    public void shouldSendEmail() throws Exception {
        messageSender.send(message);
        verify(transport, times(1)).sendMessage(Mockito.any(), Mockito.any());
        verify(transport, times(1)).close();
    }

    @Test
    public void shouldCloseConnectionOnError() throws Exception {
        doThrow(new MessagingException()).when(transport).sendMessage(Mockito.any(), Mockito.any());
        messageSender.send(message);
        verify(transport, times(1)).close();
    }
}