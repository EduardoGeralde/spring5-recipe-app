package com.eduardoportfolio.controllers;

import com.eduardoportfolio.commands.IngredientCommand;
import com.eduardoportfolio.services.IngredientService;
import com.eduardoportfolio.services.RecipeService;
import com.eduardoportfolio.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Eduardo on 22/11/17.
 */
@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService,
                                UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
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

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateRecipeIngredient(@PathVariable ("recipeId")Long recipeId,
                                         @PathVariable ("ingredientId")Long ingredientId, Model model){
        log.debug("IngredientController updateRecipeIngredient");
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId));
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredients/ingredientForm";
    }

    @PostMapping
    @RequestMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand){
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand);
        log.debug("Saved RecipeID: "+savedCommand.getRecipeId());
        log.debug("Saved IngredientID: "+savedCommand.getId());
        return "redirect:/recipe/"+savedCommand.getRecipeId()+"/ingredient/"+savedCommand.getId()+"/show";
    }
}
