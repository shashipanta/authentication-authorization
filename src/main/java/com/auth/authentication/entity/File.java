package com.auth.authentication.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "file_upload")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    private String filePath;
    private LocalDateTime uploadedAt;
    private LocalDateTime updatedAt;
}
