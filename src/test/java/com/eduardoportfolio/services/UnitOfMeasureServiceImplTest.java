package com.eduardoportfolio.services;

import com.eduardoportfolio.commands.UnitOfMeasureCommand;
import com.eduardoportfolio.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.eduardoportfolio.models.UnitOfMeasure;
import com.eduardoportfolio.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Eduardo on 26/11/17.
 */
public class UnitOfMeasureServiceImplTest {
    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    UnitOfMeasureService service;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        service = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    public void listAllUoms() throws Exception {
        //given
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();

        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom1.setId(1L);

        unitOfMeasures.add(uom1);
        unitOfMeasures.add(uom2);

        //Setting up Mockito
        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

        //when
        Set<UnitOfMeasureCommand> commands = service.listAllUoms();

        //then
        assertEquals(2, commands.size());
        verify(unitOfMeasureRepository, times(1)).findAll();

    }

}