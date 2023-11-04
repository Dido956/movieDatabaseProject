package bg.softuni.moviedatabase.controller;

import bg.softuni.moviedatabase.service.MovieService;
import bg.softuni.moviedatabase.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        //TODO: hide current role from dropdown! #DONE?
        return admin(model);
    }
    @DeleteMapping("/admin/delete/user/{userId}")
    public String deleteUser(Model model, @PathVariable Long userId){
        userService.deleteUser(userId);
        return admin(model);
    }

    @DeleteMapping("/admin/delete/movie/{movieId}")
    public String deleteMovie(Model model, @PathVariable Long movieId){
        movieService.deleteMovie(movieId);
        return admin(model);
    }
}
