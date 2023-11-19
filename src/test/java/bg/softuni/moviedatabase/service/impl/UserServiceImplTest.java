package bg.softuni.moviedatabase.service.impl;

import bg.softuni.moviedatabase.model.dto.UserRegisterDTO;
import bg.softuni.moviedatabase.model.entity.UserEntity;
import bg.softuni.moviedatabase.model.entity.enums.Role;
import bg.softuni.moviedatabase.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userRepository.deleteAll();
    }

    @Test
    void testRegisterUserSuccessful() {
        //Arrange
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setUsername("test");
        userRegisterDTO.setEmail("test@test.com");
        userRegisterDTO.setPassword("password");
        userRegisterDTO.setConfirmPassword("password");

        when(userRepository.findByUsername("test")).thenReturn(Optional.empty());
        //Act
        boolean result = userService.register(userRegisterDTO);
        //Assert
        assertTrue(result);
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void testRegisterUserWithSameUsername() {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setUsername("tester");
        when(userRepository.findByUsername("tester")).thenReturn(Optional.of(new UserEntity()));

        boolean result = userService.register(userRegisterDTO);

        assertFalse(result);
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void testChangeRoleToAdmin() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setRole(Role.DEFAULT);

        assertSame(user.getRole(), Role.DEFAULT);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        userService.changeRole(1L, "ADMIN");
        verify(userRepository, Mockito.times(1)).save(user);

        assertSame(user.getRole(), Role.ADMIN);
    }

    @Test
    void testChangeRoleToModerator() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setRole(Role.DEFAULT);

        assertSame(user.getRole(), Role.DEFAULT);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        userService.changeRole(1L, "MODERATOR");
        verify(userRepository, Mockito.times(1)).save(user);

        assertSame(user.getRole(), Role.MODERATOR);

    }

    @Test
    void testGetCurrentUserWithValidUsername() {
        UserEntity user = new UserEntity();
        user.setUsername("test");

        when(userRepository.findByUsername("test")).thenReturn(Optional.of(user));

        UserEntity retrievedUser = userService.getCurrentUser("test");

        assertEquals(user, retrievedUser);
    }

    @Test
    void testGetCurrentUserWithInvalidUsername() {
        when(userRepository.findByUsername("invaliduser")).thenReturn(Optional.empty());

        UserEntity retrievedUser = userService.getCurrentUser("invaliduser");

        assertNull(retrievedUser);
    }

    @Test
    void testGetAllUsers() {
        List<UserEntity> expectedUsers = new ArrayList<>();
        expectedUsers.add(new UserEntity("test1", "123456", "test1@email.com"));
        expectedUsers.add(new UserEntity("test2", "123456", "test2@email.com"));

        when(userRepository.findAll()).thenReturn(expectedUsers);

        List<UserEntity> retrievedUsers = userService.getAllUsers();

        assertEquals(expectedUsers, retrievedUsers);
    }

    @Test
    void testSaveUserToDB() {
        UserEntity user = new UserEntity("test1", "123456","test1@email.com");

        userService.saveUser(user);

        verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    void testDeleteUser() {
        UserEntity user = new UserEntity();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.deleteUser(1L);

        verify(userRepository, Mockito.times(1)).delete(user);
        verify(userRepository, Mockito.times(1)).findById(1L);
        assertTrue(userRepository.findById(1L).isEmpty());
    }
}