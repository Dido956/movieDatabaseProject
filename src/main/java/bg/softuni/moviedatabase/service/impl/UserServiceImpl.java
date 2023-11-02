package bg.softuni.moviedatabase.service.impl;

import bg.softuni.moviedatabase.model.dto.UserRegisterDTO;
import bg.softuni.moviedatabase.model.entity.UserRole;
import bg.softuni.moviedatabase.model.entity.enums.Role;
import bg.softuni.moviedatabase.model.entity.UserEntity;
import bg.softuni.moviedatabase.repository.UserRepository;
import bg.softuni.moviedatabase.repository.UserRoleRepository;
import bg.softuni.moviedatabase.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    @Override
    public boolean register(UserRegisterDTO userRegisterDTO) {
        UserEntity user = new UserEntity();

        if (userRegisterDTO == null) {
            return false;
        }

        String username = userRegisterDTO.getUsername();

        if (userRepository.findByUsername(username).isPresent()) {
            return false;
        }

//        UserRole defaultRole = userRoleRepository.findByRole(Role.DEFAULT).orElse(null);

        user
                .setUsername(userRegisterDTO.getUsername())
                .setEmail(userRegisterDTO.getEmail())
                .setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()))
                .setConfirmPassword(passwordEncoder.encode(userRegisterDTO.getConfirmPassword()))
                .setRole(Role.DEFAULT);
//                .setRoles(Collections.singletonList(defaultRole));

        userRepository.save(user);
        return true;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void changeRole(Long userId, String role) {
        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        switch (role) {
            case "DEFAULT" -> userEntity.setRole(Role.DEFAULT);
            case "MODERATOR" -> userEntity.setRole(Role.MODERATOR);
            case "ADMIN" -> userEntity.setRole(Role.ADMIN);

        }
        userRepository.save(userEntity);
    }
}
