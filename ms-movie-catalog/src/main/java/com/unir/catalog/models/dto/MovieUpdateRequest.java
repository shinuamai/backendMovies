package com.unir.catalog.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieUpdateRequest {
    private String title;
    private String genere;
    private String author;
    private String description;
    private String image;
    private String trailer;
}
