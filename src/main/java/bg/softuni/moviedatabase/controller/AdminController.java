package bg.softuni.moviedatabase.controller;

import bg.softuni.moviedatabase.model.entity.Movie;
import bg.softuni.moviedatabase.model.entity.UserEntity;
import bg.softuni.moviedatabase.service.MovieService;
import bg.softuni.moviedatabase.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {

    private final UserService userService;
    private final MovieService movieService;

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("movies", movieService.getAllMovies());
        return "admin";
    }

    @PostMapping("/admin/change-role")
    public String changeRole(@RequestParam Long userId, @RequestParam String newRole, Model model) {
        userService.changeRole(userId, newRole);
        return admin(model);
    }

}
