package com.eduardoportfolio.services;

import com.eduardoportfolio.commands.IngredientCommand;
import com.eduardoportfolio.converters.IngredientCommandToIngredient;
import com.eduardoportfolio.converters.IngredientToIngredientCommand;
import com.eduardoportfolio.models.Ingredient;
import com.eduardoportfolio.models.Recipe;
import com.eduardoportfolio.repositories.RecipeRepository;
import com.eduardoportfolio.repositories.UnitOfMeasureRepository;
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
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 RecipeRepository recipeRepository,
                                 UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
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

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if(!recipeOptional.isPresent()){
            log.error("Recipe Not Found, recipeID: "+command.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            //Find if the Ingredient already exist in the recipe
            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            //If the Ingredient exists, just update the Ingredient with the parameter data.
            if(ingredientOptional.isPresent()){
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUnitOfMeasure(unitOfMeasureRepository
                        .findById(command.getUnitOfMeasure().getId())
                        .orElseThrow(() -> new RuntimeException("UOM Not Found")));
            } else {
                //If not, add to the Recipe
                recipe.addIngredient(ingredientCommandToIngredient.convert(command));
            }

            //Saving the Recipe with the Ingredient Data changed (New or Updated)
            Recipe savedRecipe = recipeRepository.save(recipe);

            //To do check for fail
            return ingredientToIngredientCommand.convert(savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst()
                    .get());
        }
    }
}
