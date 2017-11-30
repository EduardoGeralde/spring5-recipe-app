package com.eduardoportfolio.controllers;

import com.eduardoportfolio.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by Eduardo on 08/11/17.
 */
@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"","/","index"})
    public String getIndexPage(Model model){
        log.debug("IndexController getIndexPage");
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
