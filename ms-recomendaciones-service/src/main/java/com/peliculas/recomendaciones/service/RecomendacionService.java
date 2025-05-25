package com.peliculas.recomendaciones.service;

import com.peliculas.recomendaciones.dto.RecomendacionDTO;
import com.peliculas.recomendaciones.model.Recomendacion;
import com.peliculas.recomendaciones.repository.RecomendacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecomendacionService {

    @Autowired
    private RecomendacionRepository recomendacionRepository;

    // CRUD Operations

    public List<RecomendacionDTO> obtenerTodasLasRecomendaciones() {
        return recomendacionRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public Optional<RecomendacionDTO> obtenerRecomendacionPorId(Long id) {
        return recomendacionRepository.findById(id)
                .map(this::convertirADTO);
    }

    public RecomendacionDTO crearRecomendacion(RecomendacionDTO recomendacionDTO) {
        Recomendacion recomendacion = convertirAEntidad(recomendacionDTO);

        // Lógica de generación de recomendación (simulada)
        if (recomendacion.getPuntuacionPredicha() == null) {
            recomendacion.setPuntuacionPredicha(generarPuntuacionPredicha());
        }

        if (recomendacion.getRazonRecomendacion() == null || recomendacion.getRazonRecomendacion().isEmpty()) {
            recomendacion.setRazonRecomendacion(generarRazonRecomendacion(recomendacion.getPeliculaId()));
        }

        Recomendacion recomendacionGuardada = recomendacionRepository.save(recomendacion);
        return convertirADTO(recomendacionGuardada);
    }

    public Optional<RecomendacionDTO> actualizarRecomendacion(Long id, RecomendacionDTO recomendacionDTO) {
        return recomendacionRepository.findById(id)
                .map(recomendacionExistente -> {
                    recomendacionExistente.setRazonRecomendacion(recomendacionDTO.getRazonRecomendacion());
                    recomendacionExistente.setPuntuacionPredicha(recomendacionDTO.getPuntuacionPredicha());
                    recomendacionExistente.setVisualizada(recomendacionDTO.getVisualizada());

                    Recomendacion actualizada = recomendacionRepository.save(recomendacionExistente);
                    return convertirADTO(actualizada);
                });
    }

    public boolean eliminarRecomendacion(Long id) {
        if (recomendacionRepository.existsById(id)) {
            recomendacionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Métodos de búsqueda específicos

    public List<RecomendacionDTO> obtenerRecomendacionesPorUsuario(Long usuarioId) {
        return recomendacionRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public List<RecomendacionDTO> obtenerRecomendacionesPorPelicula(Long peliculaId) {
        return recomendacionRepository.findByPeliculaId(peliculaId)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public List<RecomendacionDTO> obtenerRecomendacionesNoVisualizadas(Long usuarioId) {
        return recomendacionRepository.findByUsuarioIdAndVisualizadaFalse(usuarioId)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public List<RecomendacionDTO> obtenerTopRecomendaciones(Long usuarioId, Double puntuacionMinima) {
        return recomendacionRepository.findTopRecomendacionesByUsuario(usuarioId, puntuacionMinima)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Métodos de conversión

    private RecomendacionDTO convertirADTO(Recomendacion recomendacion) {
        RecomendacionDTO dto = new RecomendacionDTO();
        dto.setId(recomendacion.getId());
        dto.setUsuarioId(recomendacion.getUsuarioId());
        dto.setPeliculaId(recomendacion.getPeliculaId());
        dto.setRazonRecomendacion(recomendacion.getRazonRecomendacion());
        dto.setPuntuacionPredicha(recomendacion.getPuntuacionPredicha());
        dto.setFechaRecomendacion(recomendacion.getFechaRecomendacion());
        dto.setVisualizada(recomendacion.getVisualizada());

        // Aquí podrías agregar la lógica para obtener información de la película
        // desde el microservicio de catálogo usando Feign Client

        return dto;
    }

    private Recomendacion convertirAEntidad(RecomendacionDTO dto) {
        Recomendacion recomendacion = new Recomendacion();
        recomendacion.setId(dto.getId());
        recomendacion.setUsuarioId(dto.getUsuarioId());
        recomendacion.setPeliculaId(dto.getPeliculaId());
        recomendacion.setRazonRecomendacion(dto.getRazonRecomendacion());
        recomendacion.setPuntuacionPredicha(dto.getPuntuacionPredicha());
        recomendacion.setVisualizada(dto.getVisualizada());
        return recomendacion;
    }

    // Métodos auxiliares para lógica de recomendación (simulados)

    private Double generarPuntuacionPredicha() {
        // Lógica simple simulada - en un caso real sería más compleja
        return 3.0 + (Math.random() * 2.0); // Entre 3.0 y 5.0
    }

    private String generarRazonRecomendacion(Long peliculaId) {
        // Lógica simple simulada
        String[] razones = {
                "Basado en tus preferencias de género",
                "Usuarios con gustos similares también disfrutaron esta película",
                "Trending ahora en tu categoría favorita",
                "Recomendado por tu historial de visualización"
        };
        return razones[(int) (Math.random() * razones.length)];
    }
}