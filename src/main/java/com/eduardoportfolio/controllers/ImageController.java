package com.eduardoportfolio.controllers;

import com.eduardoportfolio.commands.RecipeCommand;
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
import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

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
        log.debug("ImageController showUploadForm");
        model.addAttribute("recipe", recipeService.findCommandById(recipeId));
        return "recipe/imageUploadForm";
    }

    @PostMapping("recipe/{recipeId}/image")
    public String handleImagePost(@PathVariable ("recipeId")Long recipeId,
                                  @RequestParam("imagefile")MultipartFile file){
        log.debug("ImageController handleImagePost");
        imageService.saveImageFile(recipeId, file);
        return "redirect:/recipe/"+recipeId+"/show";
    }

    @GetMapping("recipe/{recipeId}/recipeImage")
    public void renderImageFromDB(@PathVariable("recipeId")Long recipeId, HttpServletResponse httpServletResponse)
            throws IOException {
        log.debug("ImageController renderImageFromDB");
        RecipeCommand recipeCommand = recipeService.findCommandById(recipeId);

        if (recipeCommand.getImage() != null) {
            byte[] byteArray = new byte[recipeCommand.getImage().length];

            int i = 0;

            for (Byte wrappedByte : recipeCommand.getImage()) {
                byteArray[i++] = wrappedByte;  //auto unboxing
            }
            httpServletResponse.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, httpServletResponse.getOutputStream());
        }
    }
}

