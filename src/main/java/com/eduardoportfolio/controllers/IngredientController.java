package com.eduardoportfolio.controllers;

import com.eduardoportfolio.commands.IngredientCommand;
import com.eduardoportfolio.commands.RecipeCommand;
import com.eduardoportfolio.commands.UnitOfMeasureCommand;
import com.eduardoportfolio.services.IngredientService;
import com.eduardoportfolio.services.RecipeService;
import com.eduardoportfolio.services.UnitOfMeasureService;
import com.sun.javafx.sg.prism.NGShape;
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

    @GetMapping ("recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable("recipeId")Long recipeId, Model model){
        log.debug("IngredientController listIngredients recipeId = " + recipeId);
        //use command object to avoid lazy load errors in thymeleaf
        model.addAttribute("recipe", recipeService.findCommandById(recipeId));
        return "recipe/ingredients/list";
    }

    @GetMapping ("recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showRecipeIngredient(@PathVariable("recipeId") Long recipeId,
                                       @PathVariable("ingredientId") Long ingredientId, Model model){
        log.debug("IngredientController showRecipeIngredient");
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId));
        return "recipe/ingredients/show";
    }

    @GetMapping ("recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateRecipeIngredient(@PathVariable ("recipeId")Long recipeId,
                                         @PathVariable ("ingredientId")Long ingredientId, Model model){
        log.debug("IngredientController updateRecipeIngredient");
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId));
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredients/ingredientForm";
    }

    @PostMapping ("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand){
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand);
        log.debug("Saved RecipeID: "+savedCommand.getRecipeId());
        log.debug("Saved IngredientID: "+savedCommand.getId());
        return "redirect:/recipe/"+savedCommand.getRecipeId()+"/ingredient/"+savedCommand.getId()+"/show";
    }

    @GetMapping ("recipe/{recipeId}/ingredient/new")
    public String newRecipe(@PathVariable("recipeId")Long recipeId, Model model){
        log.debug("IngredientController newRecipe");

        //Make sure we have a good id value
        RecipeCommand recipeCommand = recipeService.findCommandById(recipeId);
        //todo raise exception if null

        //need to return back parent id for hidden form property
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(recipeId);
        model.addAttribute("ingredient", ingredientCommand);

        //init uom
        ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());

        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredients/ingredientForm";
    }

    @GetMapping ("recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable("recipeId")Long recipeId,
                                   @PathVariable("ingredientId")Long ingredientId){
        log.debug("IngredientController - Deleting IngredientId: "+ ingredientId);
        ingredientService.deleteById(recipeId, ingredientId);
        return "redirect:/recipe/"+recipeId+"/ingredients";
    }
}
