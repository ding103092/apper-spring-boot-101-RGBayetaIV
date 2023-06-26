package com.apper.request;

import lombok.Data;

@Data
public class PutAccountRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
