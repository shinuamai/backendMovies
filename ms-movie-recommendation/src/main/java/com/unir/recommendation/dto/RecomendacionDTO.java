package com.unir.recommendation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class RecomendacionDTO {

    private Long id;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;

    @NotNull(message = "El ID de la película es obligatorio")
    private Long peliculaId;

    @Size(max = 1000, message = "La razón de recomendación no puede exceder 1000 caracteres")
    private String razonRecomendacion;

    private Double puntuacionPredicha;
    private LocalDateTime fechaRecomendacion;
    private Boolean visualizada;

    // Información de la película (desde el microservicio de catálogo)
    private String tituloPelicula;
    private String generoPelicula;

    // Constructores
    public RecomendacionDTO() {}

    public RecomendacionDTO(Long usuarioId, Long peliculaId, String razonRecomendacion, Double puntuacionPredicha) {
        this.usuarioId = usuarioId;
        this.peliculaId = peliculaId;
        this.razonRecomendacion = razonRecomendacion;
        this.puntuacionPredicha = puntuacionPredicha;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getPeliculaId() { return peliculaId; }
    public void setPeliculaId(Long peliculaId) { this.peliculaId = peliculaId; }

    public String getRazonRecomendacion() { return razonRecomendacion; }
    public void setRazonRecomendacion(String razonRecomendacion) { this.razonRecomendacion = razonRecomendacion; }

    public Double getPuntuacionPredicha() { return puntuacionPredicha; }
    public void setPuntuacionPredicha(Double puntuacionPredicha) { this.puntuacionPredicha = puntuacionPredicha; }

    public LocalDateTime getFechaRecomendacion() { return fechaRecomendacion; }
    public void setFechaRecomendacion(LocalDateTime fechaRecomendacion) { this.fechaRecomendacion = fechaRecomendacion; }

    public Boolean getVisualizada() { return visualizada; }
    public void setVisualizada(Boolean visualizada) { this.visualizada = visualizada; }

    public String getTituloPelicula() { return tituloPelicula; }
    public void setTituloPelicula(String tituloPelicula) { this.tituloPelicula = tituloPelicula; }

    public String getGeneroPelicula() { return generoPelicula; }
    public void setGeneroPelicula(String generoPelicula) { this.generoPelicula = generoPelicula; }
}