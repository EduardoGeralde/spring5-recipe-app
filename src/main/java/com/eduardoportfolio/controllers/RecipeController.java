package com.eduardoportfolio.controllers;

import com.eduardoportfolio.commands.RecipeCommand;
import com.eduardoportfolio.models.Recipe;
import com.eduardoportfolio.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Eduardo on 15/11/17.
 */
@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable ("id") Long id, Model model){
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/showRecipe";
    }

    @GetMapping
    @RequestMapping("recipe/new")
    public String recipeForm(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeForm";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable Long id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(id));
        return "recipe/recipeForm";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/"+ savedCommand.getId()+ "/show";
    }

    @GetMapping
    @RequestMapping(value = "/recipe/{id}/delete")
    public String deleteById(@PathVariable("id")Long id){
        log.debug("Deleting Recipe ID: " + id);

        recipeService.deleteById(id);
        return "redirect:/";
    }
}
