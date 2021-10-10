package org.upgrad.upstac.testrequests;

import lombok.Getter;
import lombok.Setter;
import org.upgrad.upstac.users.User;
import org.upgrad.upstac.users.models.Gender;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class TestRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long requestId;

    // * : 1 mapping between request and user
    // i.e a user can raise multiple test requests
    // if @ManyToMany here would mean that *:* mapping between requests and user entity
    @ManyToOne
    private User createdBy;

    private LocalDate created = LocalDate.now();

    private RequestStatus status;

    private String name;
    private Gender gender;
    private String address;
    private Integer age;
    private String email;
    private String phoneNumber;
    private Integer pinCode;


}
