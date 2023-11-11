package bg.softuni.moviedatabase.service;

import bg.softuni.moviedatabase.model.dto.AddMovieDTO;
import bg.softuni.moviedatabase.model.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();

    void deleteMovie(Long movieId);

    Movie findByTitle(String title);

    List<Movie> getFeaturedMovies();

    Movie findById(Long id);

    boolean addMovie(AddMovieDTO addMovieDTO);
}
