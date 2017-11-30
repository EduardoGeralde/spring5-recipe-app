package com.eduardoportfolio.controllers;

import com.eduardoportfolio.services.ImageService;
import com.eduardoportfolio.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Eduardo on 29/11/17.
 */
@Slf4j
@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{recipeId}/image")
    public String showUploadForm(@PathVariable("recipeId")Long recipeId, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(recipeId));
        return "recipe/imageUploadForm";
    }

    @PostMapping("recipe/{recipeId}/image")
    public String handleImagePost(@PathVariable ("recipeId")Long recipeId,
                                  @RequestParam("imagefile")MultipartFile file){
        imageService.saveImageFile(recipeId, file);
        return "redirect:/recipe/"+recipeId+"/show";
    }
}

