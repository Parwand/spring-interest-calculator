package com.parwand.projects.Zinsrechner.controllers;


import com.parwand.projects.Zinsrechner.entities.Zins;
import com.parwand.projects.Zinsrechner.model.ZinsService;
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
    @Autowired
    ZinsService zinsrechner;
    @GetMapping("/zinsservice")
    public String index(){
        return "zinsform";
    }

    @PostMapping("/zinsservice")
    public String index(@ModelAttribute @Valid Zins zins, Errors errors, Model model){
        zinsrechner.zinsenBerechnen(zins);

        if(errors.hasErrors()){
            return "zinsform";
        }

        zinsList = zinsrechner.getZinsList();

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
