package com.eduardoportfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Eduardo on 08/11/17.
 */
@Controller
public class IndexController {

    @RequestMapping({"","/","index"})
    public String getIndexPage(){
        return "index";
    }
}
