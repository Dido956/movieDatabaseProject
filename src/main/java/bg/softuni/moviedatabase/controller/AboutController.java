package bg.softuni.moviedatabase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AboutController {

    @GetMapping("/about")
    public ModelAndView about(){

        return new ModelAndView("about");
    }
}
