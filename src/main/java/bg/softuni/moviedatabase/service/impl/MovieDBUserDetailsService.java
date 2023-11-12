package bg.softuni.moviedatabase.service.impl;

import bg.softuni.moviedatabase.model.entity.UserEntity;
import bg.softuni.moviedatabase.model.entity.enums.Role;
import bg.softuni.moviedatabase.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@AllArgsConstructor
public class MovieDBUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return userRepository
                .findByUsername(username)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found!"));
    }

    private UserDetails map(UserEntity userEntity){
        return User
                .withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(List.of(MovieDBUserDetailsService.map(userEntity.getRole())))
                .build();
    }

    private static GrantedAuthority map(Role role) {
        return new SimpleGrantedAuthority("ROLE_" + role.name());
    }
}
