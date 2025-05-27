package com.unir.catalog.services;

import com.unir.catalog.models.dao.IMovieDao;
import com.unir.catalog.models.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements IMovieService {
    @Autowired
    private IMovieDao movieDao;

    // Read
    public List<Movie> getMovies() {
        return movieDao.findAll();
    }

    // Read By One
    public Optional<Movie> getMovieById(Long id) {
        return movieDao.findById(id);
    }
}

