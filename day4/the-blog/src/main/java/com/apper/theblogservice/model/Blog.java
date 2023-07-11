package com.apper.theblogservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="BLOG")
public class Blog {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="BLOGGER_ID")
    private String bloggerId;

    @Column(name="TITLE")
    private String title;

    @Column(name="BODY")
    private String body;

    @Column(name="CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name="LAST_UPDATED")
    private LocalDateTime lastUpdate;

    @PrePersist
    public void setInitialTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        lastUpdate = now;
    }

    @PreUpdate
    public void setLastUpdate() {
        lastUpdate = LocalDateTime.now();
    }
}
