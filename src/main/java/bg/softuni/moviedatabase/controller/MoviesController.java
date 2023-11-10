package bg.softuni.moviedatabase.controller;

import bg.softuni.moviedatabase.model.entity.Movie;
import bg.softuni.moviedatabase.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/movies")
@AllArgsConstructor
public class MoviesController {
    private final MovieService movieService;

    @GetMapping("/top-movies")
    private String topMovies(){
        return "top-movies";
    }

    @GetMapping("/details/{id}")
    private String moviesDetails(Model model, @PathVariable Long id){
        Movie movie = movieService.findById(id);
        model.addAttribute(movie);
        return "details";
    }
}
