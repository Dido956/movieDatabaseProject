package bg.softuni.moviedatabase.controller;

import bg.softuni.moviedatabase.model.entity.Movie;
import bg.softuni.moviedatabase.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@WebMvcTest(HomePageController.class)
public class HomePageControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MovieService movieService;

    @InjectMocks
    private HomePageController homePageController;

    @Test
    public void testGetMoviesData() throws Exception {
        // Mocking the behavior of MovieService
        List<Movie> mockMovies = Arrays.asList(new Movie(), new Movie());
        when(movieService.getFeaturedMovies()).thenReturn(mockMovies);

        // Performing the GET request
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("movies"))
                .andExpect(model().attribute("movies", mockMovies));
    }

    @Test
    public void testSearchMovie() throws Exception {
        // Mocking the behavior of MovieService
        Movie mockMovie = new Movie();
        when(movieService.findByTitle(anyString())).thenReturn(mockMovie);

        // Performing the POST request with searchParam
        mockMvc.perform(post("/search").param("searchParam", "SomeMovieTitle"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/movies/details/" + mockMovie.getId()));
    }

    @Test
    public void testSearchMovieNotFound() throws Exception {
        // Mocking the behavior of MovieService
        when(movieService.findByTitle(anyString())).thenReturn(null);

        // Performing the POST request with searchParam
        mockMvc.perform(post("/search").param("searchParam", "NonExistentMovie"))
                .andExpect(status().isOk())
                .andExpect(view().name("/movieNotFound"));
    }
}
