package bg.softuni.moviedatabase.controller;

import bg.softuni.moviedatabase.model.entity.Actor;
import bg.softuni.moviedatabase.model.entity.Movie;
import bg.softuni.moviedatabase.model.entity.UserEntity;
import bg.softuni.moviedatabase.service.MovieService;
import bg.softuni.moviedatabase.service.UserService;
import bg.softuni.moviedatabase.util.DateAndTimeFormatter;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/movies")
@AllArgsConstructor
public class MoviesController {
    private final MovieService movieService;
    private final UserService userService;

    @GetMapping("/top-movies")
    private String topMovies() {
        return "top-movies";
    }

    @GetMapping("/details/{id}")
    private String moviesDetails(Model model, @PathVariable Long id) {
        Movie movie = movieService.findById(id);
        List<Actor> cast = movie.getCast().stream().collect(Collectors.toList());
        model.addAttribute("formattedDuration", DateAndTimeFormatter.formatDuration(movie.getDuration()));
        model.addAttribute("formattedDate", DateAndTimeFormatter.formatReleaseDate(movie.getReleaseDate()));
        model.addAttribute("cast", cast);
        model.addAttribute(movie);
        return "details";
    }

    @GetMapping("/all-movies")
    private String getAllMovies(Model model) {
        List<Movie> allMovies = movieService.getAllMovies();
        model.addAttribute("allMovies", allMovies);
        return "/all-movies";
    }

    @PostMapping("/details/{id}/add-to-favorites")
    public String favoriteMovie(@RequestParam("movieId") Long movieId,
                                @AuthenticationPrincipal UserDetails currentUser,
                                @PathVariable Long id,
                                Model model) {

        Movie movie = movieService.findById(id);
        UserEntity loggedUser = userService.getCurrentUser(currentUser.getUsername());
        List<Movie> favoriteMovies = loggedUser.getFavouriteMovies();

        boolean movieAlreadyInFavorites = loggedUser.getFavouriteMovies()
                .stream()
                .anyMatch(favoriteMovie -> favoriteMovie.getId().equals(movie.getId()));

        model.addAttribute("movieAlreadyInFavorites", movieAlreadyInFavorites);

        if (movieAlreadyInFavorites) {
            return "redirect:/movies/details/" + id;
        }

        favoriteMovies.add(movie);
        userService.saveUser(loggedUser);

        return "redirect:/movies/details/" + id;
    }

}
