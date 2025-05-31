package com.unir.recommendation.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.Getter;
import lombok.Setter;

@FeignClient(name = "ms-movie-catalog", fallback = CatalogoFeignClientFallback.class)
public interface CatalogoFeignClient {

    @GetMapping("/api/catalog/{id}")
    PeliculaDTO obtenerPeliculaPorId(@PathVariable("id") Long id);

    // DTO para recibir información de películas
    @Setter
    @Getter
    class PeliculaDTO {
        private Long idMovie;
        private String title;
        private String genere;
        private String author;
        private String description;
        private String image;
        // Constructores
        public PeliculaDTO() {}

    }
}