package com.eduardoportfolio.services;

import com.eduardoportfolio.converters.RecipeCommandToRecipe;
import com.eduardoportfolio.converters.RecipeToRecipeCommand;
import com.eduardoportfolio.exceptions.NotFoundException;
import com.eduardoportfolio.models.Recipe;
import com.eduardoportfolio.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by Eduardo on 14/11/17.
 */
public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, recipeToRecipeCommand, recipeCommandToRecipe);
    }

    @Test
    public void getRecipeByIdTest() throws Exception {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        //Setting up Mockito
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //when
        Recipe recipeReturned = recipeService.findById(1L);

        //then
        assertNotNull("Null Recipe returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void getRecipes() throws Exception {

        //given
        Recipe recipe = new Recipe();
        HashSet recipeData = new HashSet();
        recipeData.add(recipe);

        //Setting up Mockito
        when(recipeService.getRecipes()).thenReturn(recipeData);

        //when
        Set<Recipe> recipes = recipeService.getRecipes();

        //then
        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());
    }

    @Test
    public void testDeleteById() throws Exception {
        //given
        Long idToDelete = 1L;

        //when
        recipeService.deleteById(idToDelete);

        //no 'when()' , since method has void return

        //then
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }

    @Test (expected = NotFoundException.class)
    public void testRecipeByIdNotFound() throws Exception {

        Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        //should go boom
    }
}