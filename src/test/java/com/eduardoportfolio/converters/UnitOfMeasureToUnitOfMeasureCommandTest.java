package com.eduardoportfolio.converters;

import com.eduardoportfolio.commands.UnitOfMeasureCommand;
import com.eduardoportfolio.models.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Eduardo on 19/11/17.
 */
public class UnitOfMeasureToUnitOfMeasureCommandTest {
    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = new Long(1L);
    UnitOfMeasureToUnitOfMeasureCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert() throws Exception {

        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(LONG_VALUE);
        uom.setDescription(DESCRIPTION);

        //when
        UnitOfMeasureCommand uomCommand = converter.convert(uom);

        //then
        assertNotNull(uomCommand);
        assertEquals(LONG_VALUE, uomCommand.getId());
        assertEquals(DESCRIPTION, uomCommand.getDescription());
    }
}