package com.geekster.Instagram.basic.design.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Post {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String postData;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Post(String postData, User user) {
        this.postData = postData;
        this.user = user;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
}
