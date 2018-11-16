package com.phe.mail.services;


import com.phe.mail.configuration.ServiceConfiguration;
import com.phe.mail.entity.MemberDetailsList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersistenceServiceTest {

    @Mock
    private ServiceConfiguration configuration;

    @Before
    public void setUp() {
        when(configuration.getMemberDetailsFile()).thenReturn("obb_contact_details_TEST.json");
    }
    @Test
    public void shouldSendAllOBB() throws Exception {
        PersistenceService service = new PersistenceService(configuration);
        MemberDetailsList members = service.getDetailsFromFile("obb");
        members.getMemberDetails().stream().forEach(member -> System.out.println(member.getEmail()));
    }
}