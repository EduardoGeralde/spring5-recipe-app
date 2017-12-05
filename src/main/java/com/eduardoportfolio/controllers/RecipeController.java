package com.eduardoportfolio.controllers;

import com.eduardoportfolio.commands.RecipeCommand;
import com.eduardoportfolio.exceptions.NotFoundException;
import com.eduardoportfolio.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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

    @GetMapping ("recipe/{id}/show")
    public String showById(@PathVariable ("id") Long id, Model model){
        log.debug("RecipeController showById");
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/showRecipe";
    }

    @GetMapping ("recipe/new")
    public String recipeForm(Model model){
        log.debug("RecipeController recipeForm");
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeForm";
    }

    @GetMapping ("recipe/{id}/update")
    public String updateRecipe(@PathVariable Long id, Model model){
        log.debug("RecipeController updateRecipe");
        model.addAttribute("recipe", recipeService.findCommandById(id));
        return "recipe/recipeForm";
    }

    @PostMapping ("recipe")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return "recipe/recipeForm";
        }

        log.debug("RecipeController saveOrUpdate");
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/"+ savedCommand.getId()+ "/show";
    }

    @GetMapping ("recipe/{id}/delete")
    public String deleteById(@PathVariable("id")Long id){
        log.debug("Deleting Recipe ID: " + id);
        recipeService.deleteById(id);
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){
        log.error("Handling Not Found Exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}
