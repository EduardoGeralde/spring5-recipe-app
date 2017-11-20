package com.eduardoportfolio.converters;

import com.eduardoportfolio.commands.RecipeCommand;
import com.eduardoportfolio.models.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Eduardo on 19/11/17.
 */
public class RecipeToRecipeCommandTest {
    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "Description";
    public static final Integer PREPTIME = Integer.valueOf(1);
    public static final Integer COOKTIME = Integer.valueOf(2);
    public static final Integer SERVINGS = Integer.valueOf(3);
    public static final String SOURCE = "Source";
    public static final String URL = "URL";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.MODERATE;
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID_2 = 2L;
    public static final Long NOTE_ID = 9L;
    public static final Long INGR_ID_1 = 4L;
    public static final Long INGR_ID_2 = 5L;
    RecipeToRecipeCommand converter;


    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new CategoryToCategoryCommand(), new NotesToNotesCommand());
    }

    @Test
    public void testNullObjects() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObjects() throws Exception {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convert() throws Exception {

        //given
        Recipe recipe = new Recipe();
        recipe.setId(ID_VALUE);
        recipe.setDescription(DESCRIPTION);
        recipe.setDirections(DIRECTIONS);
        recipe.setServings(SERVINGS);
        recipe.setUrl(URL);
        recipe.setSource(SOURCE);
        recipe.setPrepTime(PREPTIME);
        recipe.setCookTime(COOKTIME);
        recipe.setDifficulty(DIFFICULTY);

        Notes notes = new Notes();
        notes.setId(NOTE_ID);

        Category category1 = new Category();
        category1.setId(CAT_ID_1);
        Category category2 = new Category();
        category2.setId(CAT_ID_2);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(INGR_ID_1);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGR_ID_2);

        recipe.setNotes(notes);
        recipe.getCategories().add(category1);
        recipe.getCategories().add(category2);
        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);

        //when
        RecipeCommand recipeCommand = converter.convert(recipe);

        //then
        assertNotNull(recipeCommand);
        assertEquals(ID_VALUE, recipeCommand.getId());
        assertEquals(DESCRIPTION, recipeCommand.getDescription());
        assertEquals(DIRECTIONS, recipeCommand.getDirections());
        assertEquals(SERVINGS, recipeCommand.getServings());
        assertEquals(URL, recipeCommand.getUrl());
        assertEquals(SOURCE, recipeCommand.getSource());
        assertEquals(PREPTIME, recipeCommand.getPrepTime());
        assertEquals(COOKTIME, recipeCommand.getCookTime());
        assertEquals(DIFFICULTY, recipeCommand.getDifficulty());
        assertEquals(NOTE_ID, recipeCommand.getNotes().getId());
        assertEquals(2,recipeCommand.getCategories().size());
        assertEquals(2, recipeCommand.getIngredients().size());
    }
}