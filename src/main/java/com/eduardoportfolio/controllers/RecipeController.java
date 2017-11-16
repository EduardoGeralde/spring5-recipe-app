package com.eduardoportfolio.controllers;

import com.eduardoportfolio.models.Recipe;
import com.eduardoportfolio.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Eduardo on 15/11/17.
 */
@Slf4j
@Controller
public class RecipeController {

    RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/{id}")
    public String showRecipeById(@PathVariable ("id") Long id, Model model){
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/showRecipe";
    }
}
