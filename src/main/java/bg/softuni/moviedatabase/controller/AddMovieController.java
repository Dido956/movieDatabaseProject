package bg.softuni.moviedatabase.controller;

import bg.softuni.moviedatabase.model.dto.AddMovieDTO;
import bg.softuni.moviedatabase.service.MovieService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class AddMovieController {

    private final MovieService movieService;

    @GetMapping("/add-movie")
    private String addMovie(Model model) {
        AddMovieDTO addMovieDTO = new AddMovieDTO();
        model.addAttribute("addMovieDTO", addMovieDTO);
        return "add-movie";
    }

    @PostMapping("/add-movie")
    private ModelAndView saveMovieToDB(@Valid
                                       @ModelAttribute("addMovieDTO")
                                       AddMovieDTO addMovieDTO,
                                       BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("add-movie");
        }

        boolean isAdded = movieService.addMovie(addMovieDTO);

        String view = isAdded ? "redirect:/" : "add-movie";

        return new ModelAndView(view);
    }


}
