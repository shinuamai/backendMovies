package com.unir.catalog.controllers;

import com.unir.catalog.models.entity.Movie;
import com.unir.catalog.services.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/api/catalog"})
public class MovieController {
    @Autowired
    private IMovieService movieService;

    @GetMapping({"/{id}"})
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Optional<Movie> movie = movieService.getMovieById(id);
        return movie.map(value -> ResponseEntity.ok((Movie) value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Movie> getMovie() {
        return movieService.getMovies();
    }
}
