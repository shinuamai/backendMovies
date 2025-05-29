package com.unir.catalog.controllers;

import com.unir.catalog.exceptions.ElementNotFoundException;
import com.unir.catalog.models.dto.ApiResponse;
import com.unir.catalog.models.dto.MovieUpdateRequest;
import com.unir.catalog.models.entity.Movie;
import com.unir.catalog.services.IMovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({ "/api/catalog" })
public class MovieController {

    private final IMovieService movieService;

    public MovieController(IMovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id)
                .orElseThrow(() -> new ElementNotFoundException("Película no encontrada con ID: " + id));

        return ResponseEntity.ok(new ApiResponse(true, "Película encontrada", movie));
    }

    @GetMapping
    public List<Movie> getMovie() {
        return movieService.getMovies();
    }

    // POST - Crear nueva película
    @PostMapping
    public ResponseEntity<?> createMovie(@RequestBody Movie movie) {
        Movie created = movieService.createMovie(movie);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Película creada correctamente",
                "data", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Long id,
            @RequestBody MovieUpdateRequest updateRequest) {
        Movie updated = movieService.updateMovie(id, updateRequest);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Película actualizada correctamente",
                "data", updated));
    }

    // DELETE - Eliminar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovieById(id);
        ApiResponse response = new ApiResponse(true, "Película eliminada correctamente", null);
        return ResponseEntity.ok(response);
    }

}
