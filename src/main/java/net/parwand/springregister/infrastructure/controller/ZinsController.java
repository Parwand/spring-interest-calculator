package net.parwand.springregister.infrastructure.controller;


import net.parwand.springregister.domain.zins.Zins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ZinsController {
    private int jahr = 0;
    List<Zins> zinsList = new ArrayList<>();
    @GetMapping("/zinsservice")
    public String index(){
        return "zinsform";
    }

    @PostMapping("/zinsservice")
    public String index(@ModelAttribute @Valid Zins zins, Errors errors, Model model){
        zins.zinsenBerechnen();

        if(errors.hasErrors()){
            return "zinsform";
        }

        zinsList = zins.getZinsList();

        model.addAttribute("zinsRechner", zinsList);
        model.addAttribute("zins", zins);
        model.addAttribute("checkbox", zins.isChecked());


        return "zinsform";
    }

    @ModelAttribute
    Zins zins(){
        return new Zins();
    }

}
