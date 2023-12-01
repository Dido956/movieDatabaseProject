package bg.softuni.moviedatabase.testutils;

import bg.softuni.moviedatabase.model.entity.Actor;
import bg.softuni.moviedatabase.model.entity.Director;
import bg.softuni.moviedatabase.model.entity.Movie;
import bg.softuni.moviedatabase.repository.ActorRepository;
import bg.softuni.moviedatabase.repository.DirectorRepository;
import bg.softuni.moviedatabase.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class TestDataUtil {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private ActorRepository actorRepository;

    public Movie createTestMovie(){

        Movie movie = movieRepository.save(new Movie()
                .setTitle("testTitle")
                .setRating(3.0)
                .setDirector(new Director("testDirector", LocalDate.now()))
                .setReleaseDate(LocalDate.now())
                .setImgUrl("test.jpg")
        );
        return movieRepository.save(movie);
    }

    public void cleanUp() {
        movieRepository.deleteAll();
        actorRepository.deleteAll();
        directorRepository.deleteAll();
    }
}
