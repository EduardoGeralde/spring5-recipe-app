package com.eduardoportfolio.services;

import com.eduardoportfolio.commands.IngredientCommand;
import com.eduardoportfolio.converters.IngredientToIngredientCommand;
import com.eduardoportfolio.models.Recipe;
import com.eduardoportfolio.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Eduardo on 24/11/17.
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        log.debug("IngredientServiceImpl findByRecipeIdAndIngredientId");

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if(!recipeOptional.isPresent()){
            log.error("RecipeID Not Found: "+recipeId);
            throw new RuntimeException("RecipeID Not Found");
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();
        if(!ingredientCommandOptional.isPresent()){
            log.error("IngredientID Not Found: " + ingredientId);
            throw new RuntimeException("IngredientID Not Found");
        }

        return ingredientCommandOptional.get();
    }
}
