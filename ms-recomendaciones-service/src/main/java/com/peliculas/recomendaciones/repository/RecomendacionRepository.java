package com.peliculas.recomendaciones.repository;

import com.peliculas.recomendaciones.model.Recomendacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecomendacionRepository extends JpaRepository<Recomendacion, Long> {

    // Búsqueda por usuario ID
    List<Recomendacion> findByUsuarioId(Long usuarioId);

    // Búsqueda por película ID
    List<Recomendacion> findByPeliculaId(Long peliculaId);

    // Búsquedas personalizadas
    List<Recomendacion> findByUsuarioIdAndVisualizadaFalse(Long usuarioId);

    @Query("SELECT r FROM Recomendacion r WHERE r.usuarioId = :usuarioId AND r.puntuacionPredicha >= :puntuacionMinima ORDER BY r.puntuacionPredicha DESC")
    List<Recomendacion> findTopRecomendacionesByUsuario(@Param("usuarioId") Long usuarioId, @Param("puntuacionMinima") Double puntuacionMinima);

    @Query("SELECT r FROM Recomendacion r WHERE r.peliculaId = :peliculaId AND r.razonRecomendacion LIKE %:razon%")
    List<Recomendacion> findByPeliculaIdAndRazonContaining(@Param("peliculaId") Long peliculaId, @Param("razon") String razon);
}
