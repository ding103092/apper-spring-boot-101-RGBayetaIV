package com.apper.theblogservice.payload;

import lombok.Data;

@Data
public class ErrorResponse {
    private String error;
    private String message;
}