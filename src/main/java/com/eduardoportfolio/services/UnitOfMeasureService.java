package com.eduardoportfolio.services;

import com.eduardoportfolio.commands.UnitOfMeasureCommand;

import java.util.Set;

/**
 * Created by Eduardo on 26/11/17.
 */
public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
