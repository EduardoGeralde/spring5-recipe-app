package com.eduardoportfolio.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Eduardo on 14/11/17.
 */
public class CategoryTest {

    Category category;

    @Before
    public void setUp() throws Exception {
        category = new Category();

    }

    @Test
    public void getId() throws Exception {
        Long idValue = 4L;
        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() throws Exception {

    }

    @Test
    public void getRecipes() throws Exception {

    }

}