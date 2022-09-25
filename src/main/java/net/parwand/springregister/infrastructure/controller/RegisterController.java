package net.parwand.springregister.infrastructure.controller;

import net.parwand.springregister.applicationservice.exceptions.UserAlreadyExistException;
import net.parwand.springregister.domain.User;
import net.parwand.springregister.infrastructure.app.UserDetailsServiceImpl;
import net.parwand.springregister.infrastructure.db.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private UserDetailsServiceImpl userDetailsServiceImpl;

    public RegisterController(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @ModelAttribute("user")
    public UserDto userDto() {
        return new UserDto(null, null, null, null);
    }

    @GetMapping("/user/registration")
    public String showFormRegisterUser() {
        return "formRegisterUser";
    }

    @GetMapping("/student/registration")
    public String showFormRegisterStudent() {
        return "formRegisterStudent";
    }

    @PostMapping("/user/register")
    public String registerUser(@Valid @ModelAttribute("user") UserDto userDto, Errors bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "formRegisterUser";
        }
        try {
            userDetailsServiceImpl.registerUser(new User(null, userDto.getUsername(), userDto.getPassword(), null));
        } catch (UserAlreadyExistException ex) {
            model.addAttribute("message", "An Account for that username already exits");
            return "formRegisterUser";
        }
        return "redirect:/login";
    }

    @PostMapping("/student/register")
    public String registerStudent(@Valid @ModelAttribute("user") UserDto userDto, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "formRegisterStudent";
        }
        try {
            userDetailsServiceImpl.registerStudent(new User(null, userDto.getUsername(), userDto.getPassword(), null));
        } catch (UserAlreadyExistException ex) {
            model.addAttribute("message", ex.getMessage());
            return "formRegisterStudent";
        }
        return "redirect:/login";
    }

}
