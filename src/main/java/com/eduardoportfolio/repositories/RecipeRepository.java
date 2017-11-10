package com.eduardoportfolio.repositories;

import com.eduardoportfolio.models.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Eduardo on 10/11/17.
 */
public interface RecipeRepository extends CrudRepository<Recipe,Long>{
}
