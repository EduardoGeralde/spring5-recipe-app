package com.eduardoportfolio.controllers;

import com.eduardoportfolio.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Eduardo on 22/11/17.
 */
@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;

    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable("recipeId")Long recipeId, Model model){
        log.debug("IngredientController listIngredients recipeId = " + recipeId);

        //use command object to avoid lazy load errors in thymeleaf
        model.addAttribute("recipe", recipeService.findCommandById(recipeId));

        return "recipe/ingredients/list";
    }
}
