package com.eduardoportfolio.services;

import com.eduardoportfolio.commands.RecipeCommand;
import com.eduardoportfolio.models.Recipe;

import java.util.Set;

/**
 * Created by Eduardo on 11/11/17.
 */
public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand findCommandById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    void deleteById(Long id);
}
