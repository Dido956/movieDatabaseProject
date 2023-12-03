package bg.softuni.moviedatabase.service;

import bg.softuni.moviedatabase.model.dto.UserRegisterDTO;
import bg.softuni.moviedatabase.model.entity.Movie;
import bg.softuni.moviedatabase.model.entity.UserEntity;

import java.util.List;

public interface UserService {

    boolean register(UserRegisterDTO userRegisterDTO);

    List<UserEntity> getAllUsers();

    void changeRole(Long userId, String role);

    void deleteUser(Long userId);

    UserEntity getCurrentUser(String username);

    void saveUser(UserEntity currentUser);

    void unFavouriteMovie(UserEntity currentUser, Movie movie);

    void updateProfileHits(UserEntity currentUser);
}
