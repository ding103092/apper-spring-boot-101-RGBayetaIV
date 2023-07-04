package com.example.estore;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateUserResponse(@JsonProperty("verification_code") String verificationCode) {
}
