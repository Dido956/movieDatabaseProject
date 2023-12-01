package bg.softuni.moviedatabase.service.impl;

import bg.softuni.moviedatabase.model.entity.UserEntity;
import bg.softuni.moviedatabase.model.entity.enums.Role;
import bg.softuni.moviedatabase.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieDBUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MovieDBUserDetailsService movieDBUserDetailsService;

    @Test
    public void loadUserByUsername_whenUserExists_shouldReturnUserDetails() {
        String username = "johndoe";
        UserEntity userEntity = new UserEntity(username, "password", Role.DEFAULT);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(userEntity));

        UserDetails userDetails = movieDBUserDetailsService.loadUserByUsername(username);

        assertEquals(username, userDetails.getUsername());
        assertTrue(userDetails.getPassword().matches("password"));
        assertEquals(1, userDetails.getAuthorities().size());}

    @Test
    public void loadUserByUsername_whenUserDoesNotExist_shouldThrowUsernameNotFoundException() {
        String username = "test";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> movieDBUserDetailsService.loadUserByUsername(username));
    }
}
