package com.proyect1.movies.backend.services;

import com.proyect1.movies.backend.models.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface IMovieService {
    List<Movie> getMovies();
    Optional<Movie> getMovieById(Long id);
}

