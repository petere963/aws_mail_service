package com.phe.mail.entity;

public class MemberDetails {
    private String name;
    private String email;
    private String phone;
    private String bandId;

    public String getName() {
        return name;
    }

    public MemberDetails withName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public MemberDetails withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public MemberDetails withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getBandId() {
        return bandId;
    }

    public MemberDetails withBandId(String bandId) {
        this.bandId = bandId;
        return this;
    }
}
