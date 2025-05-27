package com.unir.recommendation.client;

import org.springframework.stereotype.Component;

@Component
public class CatalogoFeignClientFallback implements CatalogoFeignClient {

    @Override
    public PeliculaDTO obtenerPeliculaPorId(Long id) {
        // Retorna información por defecto en caso de fallo
        PeliculaDTO peliculaDefault = new PeliculaDTO();
        peliculaDefault.setId(id);
        peliculaDefault.setTitulo("Película no disponible");
        peliculaDefault.setGenero("Desconocido");
        peliculaDefault.setDirector("No disponible");
        peliculaDefault.setSinopsis("Información no disponible temporalmente");
        return peliculaDefault;
    }
}