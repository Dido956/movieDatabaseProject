package bg.softuni.moviedatabase.controller;

import bg.softuni.moviedatabase.model.dto.AddMovieDTO;
import bg.softuni.moviedatabase.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AddMovieController.class)
class AddMovieControllerTestIT {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Test
    @WithMockUser
    void testAddMovieForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/add-movie"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add-movie"));
    }

    // ...

    @Test
    @WithMockUser
    void testSaveMovieToDB_Success() throws Exception {
        // Create an instance of AddMovieDTO and set values
        AddMovieDTO addMovieDTO = new AddMovieDTO();
        addMovieDTO.setTitle("Movie Title");
        addMovieDTO.setSummary("Movie SummaryMovie SummaryMovie SummaryMovie SummaryMovie SummaryMovie SummaryMovie SummaryMovie SummaryMovie SummaryMovie SummaryMovie Summary");
        addMovieDTO.setRating(8.5);
        addMovieDTO.setImageUrl("https://example.com/image.jpg");
        addMovieDTO.setDuration("2 hours 30 minutes");
        addMovieDTO.setReleaseDate(LocalDate.now());
        addMovieDTO.setDirectorName("Martin Scorsese");
        addMovieDTO.setActorNames(List.of("Leonardo DiCaprio", "Tom Hardy", "Elliot Page", "Samuel L. Jackson"));

        // Perform the POST request with the populated AddMovieDTO
        mockMvc.perform(MockMvcRequestBuilders.post("/add-movie")
                        // ... other parameters ...
                .param("title", addMovieDTO.getTitle())
                .param("summary", addMovieDTO.getSummary())
                .param("rating", String.valueOf(addMovieDTO.getRating()))
                .param("imageUrl", addMovieDTO.getImageUrl())
                .param("duration", addMovieDTO.getDuration())
                .param("releaseDate", addMovieDTO.getReleaseDate().toString())
                .param("directorName","Martin Scorsese")
                .param("actorNames",
                        String.valueOf(List.of("Leonardo DiCaprio", "Tom Hardy", "Elliot Page", "Samuel L. Jackson")))
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/")); // Assuming redirect:/
    }

}