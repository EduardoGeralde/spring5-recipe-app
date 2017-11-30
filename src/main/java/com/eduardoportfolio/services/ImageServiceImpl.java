package com.eduardoportfolio.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Eduardo on 29/11/17.
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService{

    @Override
    public void saveImageFile(Long recipeId, MultipartFile multipartFile) {
        log.debug("Received File");
    }
}
