package com.eduardoportfolio.converters;

import com.eduardoportfolio.commands.CategoryCommand;
import com.eduardoportfolio.models.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Eduardo on 19/11/17.
 */
public class CategoryToCategoryCommandTest {
    public final Long ID_VALUE = new Long(1L);
    public final String DESCRIPTION = "description";
    CategoryToCategoryCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObjects() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObjects() throws Exception {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convert() throws Exception {

        //Given
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);

        //When
        CategoryCommand categoryCommand = converter.convert(category);

        //then
        assertNotNull(category);
        assertEquals(ID_VALUE, categoryCommand.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}