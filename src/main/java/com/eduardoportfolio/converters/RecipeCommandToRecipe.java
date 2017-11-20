package com.eduardoportfolio.converters;

import com.eduardoportfolio.commands.RecipeCommand;
import com.eduardoportfolio.models.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by Eduardo on 18/11/17.
 */
@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand,Recipe>{

    private final IngredientCommandToIngredient ingredientConverter;
    private final CategoryCommandToCategory categoryConverter;
    private final NotesCommandToNotes notesConverter;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientConverter,
                                 CategoryCommandToCategory categoryConverter, NotesCommandToNotes notesConverter) {
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if (recipeCommand == null){
            return null;
        }

        Recipe recipe = new Recipe();
        recipe.setId(recipeCommand.getId());
        recipe.setServings(recipeCommand.getServings());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setSource(recipeCommand.getSource());
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setDirections(recipeCommand.getDirections());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setNotes(notesConverter.convert(recipeCommand.getNotes()));

        if (recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0){
            recipeCommand.getCategories()
                    .forEach(category -> recipe.getCategories().add((categoryConverter.convert(category))));
        }

        if (recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0){
            recipeCommand.getIngredients()
                    .forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return recipe;
    }
}
