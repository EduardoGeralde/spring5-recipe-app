package com.eduardoportfolio.controllers;

import com.eduardoportfolio.services.IngredientService;
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
    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable("recipeId")Long recipeId, Model model){
        log.debug("IngredientController listIngredients recipeId = " + recipeId);

        //use command object to avoid lazy load errors in thymeleaf
        model.addAttribute("recipe", recipeService.findCommandById(recipeId));
        return "recipe/ingredients/list";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showRecipeIngredient(@PathVariable("recipeId") Long recipeId,
                                       @PathVariable("ingredientId") Long ingredientId, Model model){
        log.debug("IngredientController showRecipeIngredient");
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId));
        return "recipe/ingredients/show";
    }
}
