package com.apper;

import lombok.Data;

@Data
public class CreateAccountRequest {
    private String firstName;
    private String lastname;
    private String email;
    private String password;
}
