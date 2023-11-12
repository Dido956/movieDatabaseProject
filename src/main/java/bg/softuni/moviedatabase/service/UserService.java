package bg.softuni.moviedatabase.service;

import bg.softuni.moviedatabase.model.dto.UserRegisterDTO;
import bg.softuni.moviedatabase.model.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {


    boolean register(UserRegisterDTO userRegisterDTO);

    List<UserEntity> getAllUsers();

    void changeRole(Long userId, String role);

    void deleteUser(Long userId);

    UserEntity getCurrentUser(String username);

    void saveUser(UserEntity currentUser);
}
