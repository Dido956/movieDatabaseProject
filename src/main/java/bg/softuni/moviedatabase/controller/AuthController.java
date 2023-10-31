package bg.softuni.moviedatabase.controller;

import bg.softuni.moviedatabase.model.dto.UserRegisterDTO;
import bg.softuni.moviedatabase.service.UserService;
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
public class AuthController {

    private final UserService userService;
    @GetMapping("/login")
    private ModelAndView login() {
        return new ModelAndView("/login");
    }

    @GetMapping("/register")
    private ModelAndView register(@ModelAttribute("userRegisterDTO") UserRegisterDTO userRegisterDTO) {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    private ModelAndView register(@Valid
                                  @ModelAttribute("userRegisterDTO")
                                  UserRegisterDTO userRegisterDTO,
                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("register");
        }

        boolean isRegistered = userService.register(userRegisterDTO);

        String view = isRegistered ? "redirect:/login" : "register";

        return new ModelAndView(view);
    }

    @PostMapping("/logout")
    private ModelAndView logout() {
        return new ModelAndView("index");
    }

    @PostMapping("/login-error")
    private String onFailure(@ModelAttribute("username") String username, Model model){
        model.addAttribute("bad_credentials", true);
        model.addAttribute("username", username);
        return "login";
    }

}
