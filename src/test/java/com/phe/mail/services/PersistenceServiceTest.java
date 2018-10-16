package com.phe.mail.services;


import com.phe.mail.entity.MemberDetailsList;
import org.junit.Test;

public class PersistenceServiceTest {

    @Test
    public void shouldSendAllOBB() throws Exception {
        PersistenceService service = new PersistenceService();
        MemberDetailsList members = service.getDetailsFromFile("obb");
        members.getMemberDetails().stream().forEach(member -> System.out.println(member.getEmail()));
    }
}