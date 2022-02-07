package com.ridwan.retaildiscountapi.entity.request;

import com.ridwan.retaildiscountapi.enums.UserType;
import lombok.Data;

import javax.validation.constraints.Email;
import java.time.LocalDate;

/**
 * @author Ridwan Mustapha
 */
@Data
public class UserRequest {

    private String firstName;
    private String lastName;

    @Email
    private String email;
    private String phoneNumber;
    private UserType userType;

    private LocalDate createdDate;
}
