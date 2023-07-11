package com.apper.theblogservice.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetBlogResponse {
    @JsonProperty("blogger_id")
    private String bloggerId;
    private String title;
    private String body;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("last_updated")
    private LocalDateTime lastUpdated;
}