package com.eduardoportfolio.repositories;

import com.eduardoportfolio.models.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by Eduardo on 10/11/17.
 */
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long>{

    Optional <UnitOfMeasure> findByDescription (String description);
}
