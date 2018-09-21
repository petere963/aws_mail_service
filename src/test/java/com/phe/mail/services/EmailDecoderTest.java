package com.phe.mail.services;

import org.junit.Test;

import javax.mail.MessagingException;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class EmailDecoderTest {

    @Test
    public void shouldFormatEmail() throws MessagingException, IOException {
        String emailString = "Return-Path: <javasound.org@gmail.com>\n" + "Received: from mail-ed1-f44.google.com (mail-ed1-f44.google.com [209.85.208.44])\n" + " by inbound-smtp.us-east-1.amazonaws.com with SMTP id 8t9o6ngrcnbdv7njml2fu1nm03grhg0b654dlp01\n" + " for test@otleybrassband.co.uk;\n" + " Thu, 20 Sep 2018 12:41:47 +0000 (UTC)\n" + "Received-SPF: pass (spfCheck: domain of _spf.google.com designates 209.85.208.44 as permitted sender) client-ip=209.85.208.44; envelope-from=javasound.org@gmail.com; helo=mail-ed1-f44.google.com;\n" + "Authentication-Results: amazonses.com;\n" + " spf=pass (spfCheck: domain of _spf.google.com designates 209.85.208.44 as permitted sender) client-ip=209.85.208.44; envelope-from=javasound.org@gmail.com; helo=mail-ed1-f44.google.com;\n" + " dkim=pass header.i=@gmail.com;\n" + " dmarc=pass header.from=gmail.com;\n" + "X-SES-RECEIPT: AEFBQUFBQUFBQUFGZWM3bDhzN2hXWnNFREdsU2dXTWREOGpWR2RHRVNqMnFFVk5NemJtZkl3TVNqdWUzVytDY21KeXJNV3JoQ0dqaUdsOGVCejlkMFZ2bWJoWXhUL0ZuWEtEem1WKzZHWUNZRUlNekFTQkNHWUhSdzZDakJXaktDaG5KaWJJbkRGS1BwVUpFaWxTdHhEVnQ5QXhaR2ZDM2k5OVF0Ykd2cXlSZHZvTXY1Vk8xM0Fvam42UlhGQzlucHZ4M0Fkd1pHU1B1dUdlTFRjV25XV3NLUGxyMWRHUy83dU5ScXhRaW43V1lWQ3VpcysyS0czMEpRNHcyVmM0TDlhTmJ6dXJCYjVhRGZ2M24weGM3TFN5NW40WlVSTEJEendLKzBGUHF2OHdkSjZXbU01SUVjYnptREx3M0JUQUdFcjhSVnNTUkQyM1E9\n" + "X-SES-DKIM-SIGNATURE: a=rsa-sha256; q=dns/txt; b=htAow0S9dpF9dxYVOohgLll0bphTdxGjV+WZH7cmgUnHa1+T4AUSlr/wls83Nl2FHSRR9JI5H0mBxv4a1BqnMwCeI7Udzfe0IVobJpOkmWgrGYNkWnQ0aCjkhk6CpANNbUqPQeIA4wsIuzuYzeDCLDGyjr6VmdRkxGI3uuRZGHE=; c=relaxed/simple; s=ug7nbtf4gccmlpwj322ax3p6ow6yfsug; d=amazonses.com; t=1537447307; v=1; bh=VyqV/unA8yADB4nkiDcHr/4SSC+7HqBLPqgmfIeokPs=; h=From:To:Cc:Bcc:Subject:Date:Message-ID:MIME-Version:Content-Type:X-SES-RECEIPT;\n" + "Received: by mail-ed1-f44.google.com with SMTP id z27-v6so7704618edb.10\n" + "        for <test@otleybrassband.co.uk>; Thu, 20 Sep 2018 05:41:47 -0700 (PDT)\n" + "DKIM-Signature: v=1; a=rsa-sha256; c=relaxed/relaxed;\n" + "        d=gmail.com; s=20161025;\n" + "        h=from:content-transfer-encoding:mime-version:subject:message-id:date\n" + "         :to;\n" + "        bh=VyqV/unA8yADB4nkiDcHr/4SSC+7HqBLPqgmfIeokPs=;\n" + "        b=BmWC6CaTPYi3vPcimMUndkCmKrxUr0VzcUUViw07DRTtAmz14WUDQCfSHAWvnp0wSE\n" + "         AAqxqCsOOaY6TCCZYSsvzjWiykkaO0xrE14Meq13a6lweQochOMVxIIJIVPMLm8cDYGP\n" + "         ACsTFA8MOaTsKobJARO4m04OlDNsMJw+zyBOgyagb/XBiItQtqVCo8oHHEy6Gi1FeI7/\n" + "         lhjHOupPn2RbEoeHb0uhfAMvmi0qgZ8EJasWu9NrP3bhZOrE8/fOx2r8JVocM/HrYQvo\n" + "         ZvgWgtwCe2wuV4LWYVWS6AB/Vll4H268R/NB0vmAVSCVptFrvTtIrXl+Sk+I6aMg8VtC\n" + "         Gujg==\n" + "X-Google-DKIM-Signature: v=1; a=rsa-sha256; c=relaxed/relaxed;\n" + "        d=1e100.net; s=20161025;\n" + "        h=x-gm-message-state:from:content-transfer-encoding:mime-version\n" + "         :subject:message-id:date:to;\n" + "        bh=VyqV/unA8yADB4nkiDcHr/4SSC+7HqBLPqgmfIeokPs=;\n" + "        b=DO2Op1rAFdUqd5rrXdEc6Xn723tvKMbi8iCzPyzeKZ+ibQfBA6dO28XmGON9NWU2kC\n" + "         AQp6JV/OMuTSYH1u4wG6Xm3AJ/nuSQLV1iGPfhGx0aTv8HIk2JUaxl1GLjM478LuY9A8\n" + "         UAlu41EmTQbYJImB4cUCM0MhqJ00lSeYAYuxzM0teo2IMiWi8Cvr3srdS8oU6ZL4EVfP\n" + "         edSSALrDRSETMwnIPrIsN4DLepXilu8Ozvp7+40MMaHBiAg/usoQVQlThRjeD4NeSr1c\n" + "         6YlAnfy+7OwUlfsk4Kl2WzGi82d7w9g+3FlWepKVwhy5N7NxyX6kCQ+Vot29KLMj08RZ\n" + "         +bRQ==\n" + "X-Gm-Message-State: APzg51BLY+qoNDjRFAGWKGepjE2HjGNfnazXEmHheIFr25uhxwzrwx3Y\n" + "NP1ruF/qpnqQUO/EUH3vd+z7Co+n\n" + "X-Google-Smtp-Source: ANB0Vdaap+c6wQQLNr2MOQtJ6IOt5KDv3vNCOoU2uQqP35trjlEh8zwonXsHJ/iVoDJievNJSNHy8g==\n" + "X-Received: by 2002:a50:97ba:: with SMTP id e55-v6mr4065476edb.58.1537447306403;\n" + "        Thu, 20 Sep 2018 05:41:46 -0700 (PDT)\n" + "Return-Path: <javasound.org@gmail.com>\n" + "Received: from [192.168.109.91] ([86.188.155.186])\n" + "        by smtp.gmail.com with ESMTPSA id h3-v6sm1622460ede.42.2018.09.20.05.41.45\n" + "        for <test@otleybrassband.co.uk>\n" + "        (version=TLS1_2 cipher=ECDHE-RSA-AES128-GCM-SHA256 bits=128/128);\n" + "        Thu, 20 Sep 2018 05:41:45 -0700 (PDT)\n" + "From: Peter Earle <javasound.org@gmail.com>\n" + "Content-Type: text/plain;\n" + "charset=us-ascii\n" + "Content-Transfer-Encoding: 7bit\n" + "Mime-Version: 1.0 (Mac OS X Mail 11.5 \\(3445.9.1\\))\n" + "Subject: Test message\n" + "Message-Id: <E8255CC9-D3D3-4A53-A0B8-C6B2F31A7078@gmail.com>\n" + "Date: Thu, 20 Sep 2018 13:41:44 +0100\n" + "To: test@otleybrassband.co.uk\n" + "X-Mailer: Apple Mail (2.3445.9.1)\n" + "\n" + "hello world\n";

        EmailDecoder decoder = new EmailDecoder(emailString);
        assertThat(decoder.getSubject(), is("Test message"));
        assertThat(decoder.getAddressCount(), is(1));
        assertThat(decoder.getAddress(0), is("Peter Earle <javasound.org@gmail.com>"));
        assertThat(decoder.getContent(), is("hello world\n"));
    }
}