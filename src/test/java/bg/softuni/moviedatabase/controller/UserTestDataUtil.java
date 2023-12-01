package bg.softuni.moviedatabase.controller;

import bg.softuni.moviedatabase.model.entity.UserEntity;
import bg.softuni.moviedatabase.model.entity.enums.Role;
import bg.softuni.moviedatabase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserTestDataUtil {
    @Autowired
    private UserRepository userRepository;


    public UserEntity createTestUser(String username){
        return createUser(username);
    }

    public UserEntity createTestAdmin(String username){
        return createAdmin(username);
    }

    private UserEntity createAdmin(String username) {
        UserEntity admin = new UserEntity();
        admin.setRole(Role.ADMIN)
                .setUsername(username)
                .setAge(21)
                .setEmail("test2@email.com")
                .setPassword("test123")
                .setConfirmPassword("test123");

        return userRepository.save(admin);
    }

    private UserEntity createUser(String username) {
        UserEntity defUser = new UserEntity();
        defUser.setRole(Role.DEFAULT)
                .setUsername(username)
                .setAge(21)
                .setEmail("test@email.com")
                .setPassword("test123")
                .setConfirmPassword("test123");

        return userRepository.save(defUser);
    }

    public void cleanUp() {
        userRepository.deleteAll();
    }
}
