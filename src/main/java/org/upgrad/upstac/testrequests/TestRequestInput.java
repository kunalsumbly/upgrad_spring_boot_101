package org.upgrad.upstac.testrequests;

import lombok.Getter;
import lombok.Setter;
import org.upgrad.upstac.users.models.Gender;

@Getter
@Setter
public class TestRequestInput {
    private String name;
    private Gender gender;
    private String address;
    private Integer age;
    private String email;
    private String phoneNumber;
    private Integer pinCode;
}
