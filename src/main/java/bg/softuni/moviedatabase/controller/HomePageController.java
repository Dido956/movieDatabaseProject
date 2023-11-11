package bg.softuni.moviedatabase.controller;

import bg.softuni.moviedatabase.model.entity.Movie;
import bg.softuni.moviedatabase.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomePageController {
    private final MovieService movieService;
    @GetMapping("/")
    public String getMoviesData(Model model) {
        List<Movie> featuredMovies = movieService.getFeaturedMovies();
        model.addAttribute("movies", featuredMovies);
        return "index";
    }

    @PostMapping("/search")
    private String searchMovie(@RequestParam("searchParam") String searchParam){
        Movie movie = movieService.findByTitle(searchParam);

        if (movie != null){
            return "redirect:/movies/details/" + movie.getId();
        }
        //TODO: redirect somewhere on movieNotFound();
        return "/movieNotFound";
    }
}
