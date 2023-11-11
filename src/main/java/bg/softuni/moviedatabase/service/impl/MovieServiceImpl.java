package bg.softuni.moviedatabase.service.impl;

import bg.softuni.moviedatabase.model.dto.AddMovieDTO;
import bg.softuni.moviedatabase.model.entity.Director;
import bg.softuni.moviedatabase.model.entity.Movie;
import bg.softuni.moviedatabase.repository.DirectorRepository;
import bg.softuni.moviedatabase.repository.MovieRepository;
import bg.softuni.moviedatabase.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static bg.softuni.moviedatabase.util.StringConstants.*;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;

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

    @Override
    public Movie findById(Long id) {
       return movieRepository.findById(id).orElse(null);
    }

    @Override
    public boolean addMovie(AddMovieDTO addMovieDTO) {
        Movie movie = new Movie();

        if (addMovieDTO == null) {
            return false;
        }
        String movieTitle = addMovieDTO.getTitle();

        if (movieRepository.findMovieByTitle(movieTitle).isPresent()){
            return false;
        }

        Director director = directorRepository.findDirectorByName(addMovieDTO.getDirectorName());

        if (director == null){
            return false;
        }
        String duration = addMovieDTO.getDuration();
        LocalTime time = LocalTime.parse(duration);
        Time parsedDuration = Time.valueOf(LocalTime.parse(duration));

        movie
                .setTitle(addMovieDTO.getTitle())
                .setDirector(director)
                .setRating(addMovieDTO.getRating())
                .setImgUrl(addMovieDTO.getImageUrl())
                .setReleaseDate(addMovieDTO.getReleaseDate())
                .setDuration(parsedDuration);

        movieRepository.save(movie);
        return true;
    }

    private boolean isFeaturedMovie(Movie movie) {
        String title = movie.getTitle();
        return title.equals(FEAUTERD_MOVIE_1) || title.equals(FEAUTERD_MOVIE_2) || title.equals(FEAUTERD_MOVIE_3) || title.equals(FEAUTERD_MOVIE_4);
    }

}
