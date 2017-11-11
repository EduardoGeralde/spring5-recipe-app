package com.eduardoportfolio.repositories;

import com.eduardoportfolio.models.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


/**
 * Created by Eduardo on 10/11/17.
 */
public interface CategoryRepository extends CrudRepository<Category, Long>{

    Optional <Category> findByDescription (String description);
}
