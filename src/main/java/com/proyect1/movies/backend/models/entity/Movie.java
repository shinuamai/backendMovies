package com.proyect1.movies.backend.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "movies"
)
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long idMovie;
    private String title;
    private String genere;
    private String author;
    private String description;
    private String image;
    @CreationTimestamp
    @Column(
            name = "created_at",
            updatable = false
    )
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(
            name = "updated_at"
    )
    private LocalDateTime updatedAt;
}
