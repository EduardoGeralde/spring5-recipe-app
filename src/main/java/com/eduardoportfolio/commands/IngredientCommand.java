package com.eduardoportfolio.commands;

import com.eduardoportfolio.models.UnitOfMeasure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by Eduardo on 18/11/17.
 */
@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {

    private Long id;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasure unitOfMeasure;

}
