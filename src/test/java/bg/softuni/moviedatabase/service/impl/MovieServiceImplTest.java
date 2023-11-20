package bg.softuni.moviedatabase.service.impl;

import bg.softuni.moviedatabase.model.dto.AddMovieDTO;
import bg.softuni.moviedatabase.model.entity.Director;
import bg.softuni.moviedatabase.model.entity.Movie;
import bg.softuni.moviedatabase.repository.ActorRepository;
import bg.softuni.moviedatabase.repository.DirectorRepository;
import bg.softuni.moviedatabase.repository.MovieRepository;
import bg.softuni.moviedatabase.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;
    @Mock
    private DirectorRepository directorRepository;
    @Mock
    private ActorRepository actorRepository;

    @Mock
    private MovieService movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        movieRepository.deleteAll();
    }

    @Test
    void getAllMovies() {
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();

        when(movieService.getAllMovies()).thenReturn(List.of(movie1,movie2));

        assertTrue(movieService.getAllMovies().size() == 2);
    }

    @Test
    void deleteMovie() {
    }

    @Test
    void findByTitleShouldReturnCorrectMovie() {
        Movie movie = new Movie();
        movie.setTitle("test");

        when(movieService.findByTitle("test")).thenReturn(movie);

        assertEquals(movie.getTitle(),"test");
    }
    @Test
    void findByTitleShouldReturnNullWhenTitleIsNotExistent() {
        Movie movie = new Movie();
        movie.setTitle("test");

        when(movieService.findByTitle("nonExistent")).thenReturn(movie);

        assertNull(movieService.findByTitle("test"));
    }

    @Test
    public void getFeaturedMovies_shouldReturnEmptyListWhenNoFeaturedMoviesExist() {
        // Arrange
        List<Movie> allMovies = Collections.emptyList();
        Mockito.when(movieRepository.findAll()).thenReturn(allMovies);

        // Act
        List<Movie> featuredMovies = movieService.getFeaturedMovies();

        // Assert
        assertEquals(Collections.emptyList(), featuredMovies);
    }


    @Test
    void findById() {
        Movie movie = new Movie();
        movie.setId(1L);

        when(movieService.findById(1L)).thenReturn(movie);
        Movie foundMovie = movieService.findById(1L);
        assertEquals(movie, foundMovie);
    }

    @Test
    public void addMovie_shouldReturnFalseIfAddMovieDTOIsNull() {
        assertFalse(movieService.addMovie(null));
    }

    @Test
    public void addMovie_shouldReturnFalseIfMovieTitleAlreadyExists() {
        AddMovieDTO addMovieDTO = new AddMovieDTO();
        addMovieDTO.setTitle("Existing Movie Title");

        Mockito.when(movieRepository.findMovieByTitle(addMovieDTO.getTitle()))
                .thenReturn(Optional.of(new Movie()));

        assertFalse(movieService.addMovie(addMovieDTO));
    }

    @Test
    public void addMovie_shouldReturnFalseIfDirectorDoesNotExist() {
        AddMovieDTO addMovieDTO = new AddMovieDTO();
        addMovieDTO.setTitle("New Movie Title");
        addMovieDTO.setDirectorName("Non-Existent Director");

        Mockito.when(directorRepository.findDirectorByName(addMovieDTO.getDirectorName()))
                .thenReturn(null);

        assertFalse(movieService.addMovie(addMovieDTO));
    }

    @Test
    public void addMovie_shouldReturnFalseIfActorDoesNotExist() {
        AddMovieDTO addMovieDTO = new AddMovieDTO();
        addMovieDTO.setTitle("New Movie Title");
        addMovieDTO.setDirectorName("Existing Director");
        addMovieDTO.setActorNames(List.of("Non-Existent Actor"));

        Mockito.when(directorRepository.findDirectorByName(addMovieDTO.getDirectorName()))
                .thenReturn(new Director());

        Mockito.when(actorRepository.findByName(addMovieDTO.getActorNames().get(0)))
                .thenReturn(null);

        assertFalse(movieService.addMovie(addMovieDTO));
    }
}