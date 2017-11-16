package com.eduardoportfolio.services;

import com.eduardoportfolio.models.Recipe;

import java.util.Set;

/**
 * Created by Eduardo on 11/11/17.
 */
public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);
}
