package com.proyect1.movies.backend.models.dao;

import com.proyect1.movies.backend.models.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieDao extends JpaRepository<Movie, Long> {
    
}
