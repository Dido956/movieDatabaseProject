package bg.softuni.moviedatabase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movies")
public class MoviesController {

    @GetMapping("/top-movies")
    private String topMovies(){
        return "top-movies";
    }
}
