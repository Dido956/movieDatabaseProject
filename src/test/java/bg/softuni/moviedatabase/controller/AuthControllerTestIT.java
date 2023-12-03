package bg.softuni.moviedatabase.controller;

import bg.softuni.moviedatabase.model.dto.UserRegisterDTO;
import bg.softuni.moviedatabase.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.endsWith;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @Test
    void loginGet() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/login"));

        resultActions
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    void registerSuccess() throws Exception {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setUsername("testUser");
        userRegisterDTO.setEmail("test@email.com");
        userRegisterDTO.setPassword("testPassword");
        userRegisterDTO.setConfirmPassword("testPassword");

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .with(csrf())
                        .flashAttr("userRegisterDTO", userRegisterDTO))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }


    @Test
    @WithMockUser
    void logout() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/logout").with(csrf()));

        resultActions.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void loginError() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/login-error")
                .with(csrf())
                .param("username", "testUser"));

        resultActions
                .andExpect(model().attribute("bad_credentials", true))
                .andExpect(model().attribute("username", "testUser"));
    }
}
