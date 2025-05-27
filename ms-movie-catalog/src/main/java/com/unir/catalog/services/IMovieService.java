package com.unir.catalog.services;

import com.unir.catalog.models.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface IMovieService {
    List<Movie> getMovies();
    Optional<Movie> getMovieById(Long id);
}

