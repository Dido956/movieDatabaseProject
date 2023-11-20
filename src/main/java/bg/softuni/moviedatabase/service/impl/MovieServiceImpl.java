package bg.softuni.moviedatabase.service.impl;

import bg.softuni.moviedatabase.model.dto.AddMovieDTO;
import bg.softuni.moviedatabase.model.entity.Actor;
import bg.softuni.moviedatabase.model.entity.Director;
import bg.softuni.moviedatabase.model.entity.Movie;
import bg.softuni.moviedatabase.repository.ActorRepository;
import bg.softuni.moviedatabase.repository.DirectorRepository;
import bg.softuni.moviedatabase.repository.MovieRepository;
import bg.softuni.moviedatabase.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.*;

import static bg.softuni.moviedatabase.util.StringConstants.*;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;
    private final ActorRepository actorRepository;

    @Override
    public List<Movie> getAllMovies() {
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

        if (movieRepository.findMovieByTitle(movieTitle).isPresent()) {
            return false;
        }

        Director director = directorRepository.findDirectorByName(addMovieDTO.getDirectorName());

        if (director == null) {
            return false;
        }

        List<String> actorNames = addMovieDTO.getActorNames();
        Set<Actor> actorsSet = new HashSet<>();

        for (String actorName : actorNames) {
            Actor actor = actorRepository.findByName(actorName);

            if (actor == null) {
                //TODO: save actor
                continue;
            }

            actorsSet.add(actor);
        }
        mapMovie(addMovieDTO, movie, director, actorsSet);

        movieRepository.save(movie);
        return true;
    }

    private static void mapMovie(AddMovieDTO addMovieDTO, Movie movie, Director director, Set<Actor> actorSet) {
        movie
                .setTitle(addMovieDTO.getTitle())
                .setRating(addMovieDTO.getRating())
                .setImgUrl(addMovieDTO.getImageUrl())
                .setReleaseDate(addMovieDTO.getReleaseDate())
                .setSummary(addMovieDTO.getSummary())
                .setDirector(director)
                .setDuration(parseTime(addMovieDTO))
                .setCast(actorSet);
    }

    private static Time parseTime(AddMovieDTO addMovieDTO) {
        String duration = addMovieDTO.getDuration();
        LocalTime time = LocalTime.parse(duration);
        return Time.valueOf(time);
    }

    private boolean isFeaturedMovie(Movie movie) {
        String title = movie.getTitle();
        return title.equals(FEAUTERD_MOVIE_1) || title.equals(FEAUTERD_MOVIE_2) || title.equals(FEAUTERD_MOVIE_3) || title.equals(FEAUTERD_MOVIE_4);
    }

}
