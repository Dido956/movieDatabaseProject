package bg.softuni.moviedatabase.controller;

import bg.softuni.moviedatabase.model.dto.UserProfileDTO;
import bg.softuni.moviedatabase.model.entity.UserEntity;
import bg.softuni.moviedatabase.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class UserProfileController {

    private final UserService userService;

    @GetMapping("/profile")
    private String loadProfileData(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        UserEntity user = userService.getCurrentUser(currentUser.getUsername());
        UserProfileDTO userProfileDTO = map(user);
        model.addAttribute(userProfileDTO);
        return "profile";
    }

    public UserProfileDTO map(UserEntity userEntity) {
        return new UserProfileDTO()
                .setEmail(userEntity.getEmail())
                .setCountry(userEntity.getCountry())
                .setAge(userEntity.getAge())
                .setFavMovies(userEntity.getFavouriteMovies());
    }
}
