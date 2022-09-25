package net.parwand.springregister.infrastructure.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class WebController {


    @GetMapping("/")
    public String index(Principal principal, Model model) {
        model.addAttribute("principal", principal);
        return "index";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin")
    public String admin(Principal principal, Model model) {
        model.addAttribute("principal", principal.getName());
        return "admin";
    }

    @Secured({"ROLE_ADMIN", "ROLE_STUDENT"})
    @GetMapping("/student")
    public String student(Principal principal, Model model) {
        model.addAttribute("principal", principal.getName());
        return "student";
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/user")
    public String user(@AuthenticationPrincipal OAuth2User oAuth2User, Principal principal, Model model) {
        model.addAttribute("principal", oAuth2User != null ? oAuth2User.getAttribute("login") : principal.getName());
        return "user";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/game")
    public String game() {
        return "tic-tac-toe";
    }

}