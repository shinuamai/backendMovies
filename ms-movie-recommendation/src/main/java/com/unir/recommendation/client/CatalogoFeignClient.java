package com.unir.recommendation.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-movie-catalog", fallback = CatalogoFeignClientFallback.class)
public interface CatalogoFeignClient {

    @GetMapping("/api/peliculas/{id}")
    PeliculaDTO obtenerPeliculaPorId(@PathVariable("id") Long id);

    // DTO para recibir información de películas
    class PeliculaDTO {
        private Long id;
        private String titulo;
        private String genero;
        private String director;
        private String sinopsis;

        // Constructores
        public PeliculaDTO() {}

        // Getters y Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getTitulo() { return titulo; }
        public void setTitulo(String titulo) { this.titulo = titulo; }

        public String getGenero() { return genero; }
        public void setGenero(String genero) { this.genero = genero; }

        public String getDirector() { return director; }
        public void setDirector(String director) { this.director = director; }

        public String getSinopsis() { return sinopsis; }
        public void setSinopsis(String sinopsis) { this.sinopsis = sinopsis; }
    }
}