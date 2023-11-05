package bg.softuni.moviedatabase.service.impl;

import bg.softuni.moviedatabase.model.entity.Movie;
import bg.softuni.moviedatabase.repository.MovieRepository;
import bg.softuni.moviedatabase.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static bg.softuni.moviedatabase.util.StringConstants.*;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    @Override
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    @Override
    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    @Override
    public Movie findByTitle(String title) {
      return movieRepository
              .findMovieByTitle(title)
              .orElse(null);
    }

    @Override
    public List<Movie> getFeaturedMovies() {
        List<Movie> allMovies = movieRepository.findAll();
        List<Movie> featuredMovies = new ArrayList<>();

        for (Movie movie : allMovies) {
            if (isFeaturedMovie(movie)) {
                featuredMovies.add(movie);
            }
        }
        return featuredMovies;
    }

    private boolean isFeaturedMovie(Movie movie) {
        String title = movie.getTitle();
        return title.equals(FEAUTERD_MOVIE_1) || title.equals(FEAUTERD_MOVIE_2) || title.equals(FEAUTERD_MOVIE_3) || title.equals(FEAUTERD_MOVIE_4);
    }

}
