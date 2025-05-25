package com.peliculas.recomendaciones.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "recomendaciones")
public class Recomendacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "usuario_id")
    private Long usuarioId;

    @NotNull
    @Column(name = "pelicula_id")
    private Long peliculaId;

    @Size(max = 1000)
    @Column(name = "razon_recomendacion")
    private String razonRecomendacion;

    @Column(name = "puntuacion_predicha")
    private Double puntuacionPredicha;

    @Column(name = "fecha_recomendacion")
    private LocalDateTime fechaRecomendacion;

    @Column(name = "visualizada")
    private Boolean visualizada = false;

    @PrePersist
    protected void onCreate() {
        fechaRecomendacion = LocalDateTime.now();
    }

    // Constructores
    public Recomendacion() {}

    public Recomendacion(Long usuarioId, Long peliculaId, String razonRecomendacion, Double puntuacionPredicha) {
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
}
