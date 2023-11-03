package bg.softuni.moviedatabase.service.impl;

import bg.softuni.moviedatabase.model.entity.Movie;
import bg.softuni.moviedatabase.repository.MovieRepository;
import bg.softuni.moviedatabase.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
