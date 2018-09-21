package com.phe.mail.services;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.phe.mail.configuration.ServiceConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MessageControllerTest {

    @Mock
    S3FileService s3FileService;

    @Mock
    MailProcessor mailProcessor;

    @Mock
    S3ObjectSummary s3ObjectSummary;

    @Mock
    ServiceConfiguration configuration;

    @Test
    public void shouldProcessEmails() {
        MessageController controller = new MessageController(s3FileService, mailProcessor, configuration);
        controller.executeProcess();
        verify(s3FileService, times(1)).getEmails();

        List<S3ObjectSummary> files = new ArrayList<>();
        files.add(s3ObjectSummary);
        when(s3FileService.getEmails()).thenReturn(files);
        when(s3FileService.extractEmail(s3ObjectSummary)).thenReturn("someEmailText");
        controller.executeProcess();
        verify(s3FileService, times(1)).extractEmail(s3ObjectSummary);
        verify(s3FileService, times(1)).deleteFile(s3ObjectSummary);
        verify(mailProcessor, times(1)).forwardEmail("someEmailText");
    }
}