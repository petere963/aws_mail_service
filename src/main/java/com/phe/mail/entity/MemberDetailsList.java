package com.phe.mail.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peter.earle on 13/06/2018.
 */
public class MemberDetailsList {

    private List<MemberDetails> memberDetails;

    public List<MemberDetails> getMemberDetails() {
        if(memberDetails == null) {
            memberDetails = new ArrayList<>();
        }
        return memberDetails;
    }

    public MemberDetailsList withMemberDetails(List<MemberDetails> memberDetails) {
        this.memberDetails = memberDetails;
        return this;
    }
}
