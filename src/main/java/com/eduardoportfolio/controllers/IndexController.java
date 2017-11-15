package com.eduardoportfolio.controllers;

import com.eduardoportfolio.models.Category;
import com.eduardoportfolio.models.UnitOfMeasure;
import com.eduardoportfolio.repositories.CategoryRepository;
import com.eduardoportfolio.repositories.UnitOfMeasureRepository;
import com.eduardoportfolio.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

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

    @RequestMapping({"","/","index"})
    public String getIndexPage(Model model){

        log.debug("Getting Index Page");

        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}
