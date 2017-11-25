package com.eduardoportfolio.services;

import com.eduardoportfolio.commands.IngredientCommand;

/**
 * Created by Eduardo on 24/11/17.
 */
public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
