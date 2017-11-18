package com.eduardoportfolio.converters;


import com.eduardoportfolio.commands.CategoryCommand;
import com.eduardoportfolio.models.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


/**
 * Created by Eduardo on 18/11/17.
 */
@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand,Category>{

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand categoryCommand) {
        if (categoryCommand == null){
            return null;
        }

        final Category category = new Category();
        category.setId(categoryCommand.getId());
        category.setDescription(categoryCommand.getDescription());
        return category;
    }
}
