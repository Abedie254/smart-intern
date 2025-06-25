package com.abednego.smartIntern.dto;

import com.abednego.smartIntern.model.Role;
import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class EmployerUpdateDto{

    @Email(message = "Email should be valid")
    private String email;

    private String firstName;

    private String lastName;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    private String phone;

    private Role role;

    private String companyName;

    private String companyWebsite;

    private String contactPerson;

    private String industry;

    private String location;
}
