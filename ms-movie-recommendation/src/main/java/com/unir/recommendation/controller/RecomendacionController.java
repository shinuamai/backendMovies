package com.unir.recommendation.controller;

import com.unir.recommendation.client.CatalogoFeignClient.PeliculaDTO;
import com.unir.recommendation.dto.RecomendacionDTO;
import com.unir.recommendation.service.RecomendacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recommendation")
@CrossOrigin(origins = "*")
public class RecomendacionController {

    @Autowired
    private RecomendacionService recomendacionService;

    // GET - Obtener todas las recomendaciones
    @GetMapping
    public ResponseEntity<List<RecomendacionDTO>> obtenerTodasLasRecomendaciones() {
        List<RecomendacionDTO> recomendaciones = recomendacionService.obtenerTodasLasRecomendaciones();
        return ResponseEntity.ok(recomendaciones);
    }

    // GET - Obtener recomendación por ID
    @GetMapping("/{id}")
    public ResponseEntity<RecomendacionDTO> obtenerRecomendacionPorId(@PathVariable Long id) {
        Optional<RecomendacionDTO> recomendacion = recomendacionService.obtenerRecomendacionPorId(id);
        return recomendacion
                .map(r -> ResponseEntity.ok(r))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - Crear nueva recomendación
    @PostMapping
    public ResponseEntity<RecomendacionDTO> crearRecomendacion(@Valid @RequestBody RecomendacionDTO recomendacionDTO) {
        try {
            RecomendacionDTO nuevaRecomendacion = recomendacionService.crearRecomendacion(recomendacionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaRecomendacion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // PUT - Actualizar recomendación existente
    @PutMapping("/{id}")
    public ResponseEntity<RecomendacionDTO> actualizarRecomendacion(
            @PathVariable Long id,
            @Valid @RequestBody RecomendacionDTO recomendacionDTO) {
        Optional<RecomendacionDTO> recomendacionActualizada = recomendacionService.actualizarRecomendacion(id, recomendacionDTO);
        return recomendacionActualizada
                .map(r -> ResponseEntity.ok(r))
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE - Eliminar recomendación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRecomendacion(@PathVariable Long id) {
        boolean eliminada = recomendacionService.eliminarRecomendacion(id);
        return eliminada ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // GET - Búsquedas específicas

    // Buscar por usuario ID
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<RecomendacionDTO>> obtenerRecomendacionesPorUsuario(@PathVariable Long usuarioId) {
        List<RecomendacionDTO> recomendaciones = recomendacionService.obtenerRecomendacionesPorUsuario(usuarioId);
        return ResponseEntity.ok(recomendaciones);
    }

    // Buscar por película ID
    @GetMapping("/pelicula/{peliculaId}")
    public ResponseEntity<List<RecomendacionDTO>> obtenerRecomendacionesPorPelicula(@PathVariable Long peliculaId) {
        List<RecomendacionDTO> recomendaciones = recomendacionService.obtenerRecomendacionesPorPelicula(peliculaId);
        return ResponseEntity.ok(recomendaciones);
    }
    // Buscar por película ID
    @GetMapping("/movie/{peliculaId}")
    public ResponseEntity<PeliculaDTO> getByFromMSCatalog(@PathVariable Long peliculaId) {
        PeliculaDTO movie = recomendacionService.getMovieById(peliculaId);
        return ResponseEntity.ok(movie);
    }

    // Obtener recomendaciones no visualizadas por usuario
    @GetMapping("/usuario/{usuarioId}/no-visualizadas")
    public ResponseEntity<List<RecomendacionDTO>> obtenerRecomendacionesNoVisualizadas(@PathVariable Long usuarioId) {
        List<RecomendacionDTO> recomendaciones = recomendacionService.obtenerRecomendacionesNoVisualizadas(usuarioId);
        return ResponseEntity.ok(recomendaciones);
    }

    // Obtener top recomendaciones por usuario con puntuación mínima
    @GetMapping("/usuario/{usuarioId}/top")
    public ResponseEntity<List<RecomendacionDTO>> obtenerTopRecomendaciones(
            @PathVariable Long usuarioId,
            @RequestParam(defaultValue = "4.0") Double puntuacionMinima) {
        List<RecomendacionDTO> recomendaciones = recomendacionService.obtenerTopRecomendaciones(usuarioId, puntuacionMinima);
        return ResponseEntity.ok(recomendaciones);
    }

    // POST - Generar recomendaciones automáticas para un usuario
    @PostMapping("/generar/{usuarioId}")
    public ResponseEntity<List<RecomendacionDTO>> generarRecomendacionesParaUsuario(@PathVariable Long usuarioId) {
        // Este endpoint podría implementar lógica más compleja de machine learning
        // Por ahora, creamos algunas recomendaciones de ejemplo
        try {
            // Aquí iría la lógica de generación de recomendaciones basada en ML
            // Por simplicidad, creamos recomendaciones de ejemplo

            return ResponseEntity.ok().build(); // Implementar lógica específica
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // PUT - Marcar recomendación como visualizada
    @PutMapping("/{id}/marcar-visualizada")
    public ResponseEntity<RecomendacionDTO> marcarComoVisualizada(@PathVariable Long id) {
        Optional<RecomendacionDTO> recomendacion = recomendacionService.obtenerRecomendacionPorId(id);
        if (recomendacion.isPresent()) {
            RecomendacionDTO dto = recomendacion.get();
            dto.setVisualizada(true);
            Optional<RecomendacionDTO> actualizada = recomendacionService.actualizarRecomendacion(id, dto);
            return actualizada
                    .map(r -> ResponseEntity.ok(r))
                    .orElse(ResponseEntity.notFound().build());
        }
        return ResponseEntity.notFound().build();
    }

    // GET - Health check endpoint
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Microservicio de Recomendaciones funcionando correctamente");
    }
}