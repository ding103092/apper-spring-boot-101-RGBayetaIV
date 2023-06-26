package com.apper.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PutAccountResponse {
    private LocalDateTime lastUpdated;
}
