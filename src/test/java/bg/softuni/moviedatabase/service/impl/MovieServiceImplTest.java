package bg.softuni.moviedatabase.service.impl;

import bg.softuni.moviedatabase.model.dto.AddMovieDTO;
import bg.softuni.moviedatabase.model.entity.Movie;
import bg.softuni.moviedatabase.repository.DirectorRepository;
import bg.softuni.moviedatabase.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
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

    @InjectMocks
    private MovieServiceImpl movieService;

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

        assertEquals(2, movieService.getAllMovies().size());
    }

    @Test
    void findByTitleShouldReturnCorrectMovie() {
        String title = "TestTitle";

        Movie expectedMovie = new Movie();
        expectedMovie.setTitle(title);

        Mockito.when(movieRepository.findMovieByTitle(title)).thenReturn(Optional.of(expectedMovie));

        Movie actualMovie = movieService.findByTitle(title);


        assertNotNull(actualMovie);
        assertEquals(expectedMovie, actualMovie);
    }
    @Test
    void findByTitleShouldReturnNullWhenTitleIsNotExistent() {
        Movie movie = new Movie();
        movie.setTitle("test");

        when(movieRepository.findMovieByTitle("nonExistent")).thenReturn(Optional.empty());

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

        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
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


}