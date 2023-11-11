package bg.softuni.moviedatabase.controller;

import bg.softuni.moviedatabase.model.dto.AddMovieDTO;
import bg.softuni.moviedatabase.model.entity.Actor;
import bg.softuni.moviedatabase.model.entity.Movie;
import bg.softuni.moviedatabase.service.MovieService;
import bg.softuni.moviedatabase.util.DateAndTimeFormatter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/movies")
@AllArgsConstructor
public class MoviesController {
    private final MovieService movieService;

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

}
