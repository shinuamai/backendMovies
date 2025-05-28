package com.unir.recommendation.client;

import org.springframework.stereotype.Component;

@Component
public class CatalogoFeignClientFallback implements CatalogoFeignClient {

    @Override
    public PeliculaDTO obtenerPeliculaPorId(Long id) {
        // Retorna información por defecto en caso de fallo
        PeliculaDTO peliculaDefault = new PeliculaDTO();
        peliculaDefault.setIdMovie(id);
        peliculaDefault.setTitle("Película no disponible");
        peliculaDefault.setAuthor("Desconocido");
        peliculaDefault.setDescription("No disponible");
        peliculaDefault.setImage("https://example.com/default-image.jpg");
        peliculaDefault.setGenere("Desconocido");
        return peliculaDefault;
    }
}