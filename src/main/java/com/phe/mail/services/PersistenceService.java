package com.phe.mail.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phe.mail.configuration.ServiceConfiguration;
import com.phe.mail.entity.MemberDetails;
import com.phe.mail.entity.MemberDetailsList;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PersistenceService {

    private final String PERSISTENCE_FILE;
    private ObjectMapper objectMapper = new ObjectMapper();

    public PersistenceService(ServiceConfiguration configuration) {
        PERSISTENCE_FILE = configuration.getMemberDetailsFile();
    }


    public MemberDetailsList getDetailsFromFile(String bandId) throws Exception {

        // When run for first time ....
        if(!new File(PERSISTENCE_FILE).exists()) {
            return new MemberDetailsList();
        }

        String json =  new String(Files.readAllBytes(Paths.get(PERSISTENCE_FILE)));

        if(StringUtils.isEmpty(json)) {
            return new MemberDetailsList();
        } else {
            MemberDetailsList memberDetailsList =  objectMapper.readValue(json, MemberDetailsList.class);
            return filter(bandId, memberDetailsList);
        }
    }

    private MemberDetailsList filter(String bandId, MemberDetailsList memberDetailsList) {

        List<MemberDetails> memberDetails = new ArrayList<>();
        for(MemberDetails md : memberDetailsList.getMemberDetails()) {

            // Work around for obb
            if((md.getBandId() == null) || md.getBandId().isEmpty()) {
                md.withBandId("obb");
            }

            if(md.getBandId().equalsIgnoreCase(bandId)){
                memberDetails.add(md);
            }
        }

        return new MemberDetailsList().withMemberDetails(memberDetails);
    }
}
