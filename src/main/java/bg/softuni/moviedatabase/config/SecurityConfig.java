package bg.softuni.moviedatabase.config;

import bg.softuni.moviedatabase.model.entity.enums.Role;
import bg.softuni.moviedatabase.repository.UserRepository;
import bg.softuni.moviedatabase.service.impl.MovieDBUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


@Configuration
public class SecurityConfig {
    private final String rememberMeKey;

    public SecurityConfig(@Value("${movieDB.remember.me.key}") String rememberMeKey) {
        this.rememberMeKey = rememberMeKey;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //Define which urls are visible
        httpSecurity.authorizeHttpRequests(

                        authorizeRequests -> authorizeRequests
                                //allow static resources to anyone
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                //allow anyone to see homepage,login and register
                                .requestMatchers("/", "/login-error","/movies/all-movies","/movies/details/**").permitAll()
                                .requestMatchers("login", "register").permitAll()
                                .requestMatchers("/admin").hasRole(Role.ADMIN.name())
                                //everything else is authenticated
                                .anyRequest().authenticated()
                )

                .formLogin(
                        formLogin -> formLogin
                                //redirect here when unauthorized
                                .loginPage("/login")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/")
                                .failureForwardUrl("/login-error")
                )

                .logout(
                        logout -> logout
                                .deleteCookies("JSESSIONID", "rememberme") // Exclude "rememberme" cookie
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/")
                                .invalidateHttpSession(true)
                )
                .rememberMe(
                        rememberMe -> rememberMe
//                                    .alwaysRemember(true)
//                                    .tokenValiditySeconds(86400)
                                .key(rememberMeKey)
                                .rememberMeParameter("rememberme")
                                .rememberMeCookieName("rememberme")
                );

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new MovieDBUserDetailsService(userRepository);
    }

    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

}
