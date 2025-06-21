package com.unir.catalog.services;

import com.unir.catalog.exceptions.ElementNotFoundException;
import com.unir.catalog.models.dao.IMovieDao;
import com.unir.catalog.models.dto.MovieUpdateRequest;
import com.unir.catalog.models.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements IMovieService {

    private final IMovieDao movieDao;

    public MovieService(IMovieDao movieDao) {
        this.movieDao = movieDao;
    }

    // Read
    public List<Movie> getMovies() {
        return movieDao.findAll();
    }

    // Read By One
    public Optional<Movie> getMovieById(Long id) {
        return movieDao.findById(id);
    }

    // Create
    public Movie createMovie(Movie movie) {
        return movieDao.save(movie);
    }

    // Update
    public Movie updateMovie(Long id, MovieUpdateRequest updateRequest) {
        Movie movie = movieDao.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Película no encontrada con ID: " + id));

        if (updateRequest.getTitle() != null)
            movie.setTitle(updateRequest.getTitle());
        if (updateRequest.getGenere() != null)
            movie.setGenere(updateRequest.getGenere());
        if (updateRequest.getAuthor() != null)
            movie.setAuthor(updateRequest.getAuthor());
        if (updateRequest.getDescription() != null)
            movie.setDescription(updateRequest.getDescription());
        if (updateRequest.getImage() != null)
            movie.setImage(updateRequest.getImage());
        if (updateRequest.getTrailer() != null)
            movie.setTrailer(updateRequest.getTrailer());

        return movieDao.save(movie);
    }

    // Delete
    public void deleteMovieById(Long id) {
        if (!movieDao.existsById(id)) {
            throw new ElementNotFoundException("Película no encontrada con ID: " + id);
        }
        movieDao.deleteById(id);
    }
}
