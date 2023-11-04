package bg.softuni.moviedatabase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ProfileController {

    @GetMapping("/profile")
    private String openProfile(){
        return "profile";
    }
}
