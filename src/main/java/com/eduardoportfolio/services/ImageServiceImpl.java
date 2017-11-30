package com.eduardoportfolio.services;

import com.eduardoportfolio.models.Recipe;
import com.eduardoportfolio.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Eduardo on 29/11/17.
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService{

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeService) {
        this.recipeRepository = recipeService;
    }

    @Transactional
    @Override
    public void saveImageFile(Long recipeId, MultipartFile multipartFile) {
        try{
            Recipe recipe = recipeRepository.findById(recipeId).get();

            Byte[] byteObjects = new Byte[multipartFile.getBytes().length];

            int i = 0;

            for(byte b : multipartFile.getBytes()){
                byteObjects[i++] = b;
            }

            recipe.setImage(byteObjects);

            recipeRepository.save(recipe);
        } catch (IOException e) {
            //todo handle better
            log.error("Error occurred", e);
            e.printStackTrace();
        }
    }
}
