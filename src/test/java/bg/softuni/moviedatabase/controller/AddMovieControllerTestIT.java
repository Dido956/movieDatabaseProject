package bg.softuni.moviedatabase.controller;

import bg.softuni.moviedatabase.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
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

    @Test
    void testAddMovieFormShouldGiveClientErrorWithoutUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/add-movie").with(csrf()))
                .andExpect(status().is4xxClientError());
    }

}