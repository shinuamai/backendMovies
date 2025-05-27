package com.unir.catalog.models.dao;

import com.unir.catalog.models.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieDao extends JpaRepository<Movie, Long> {
    
}
