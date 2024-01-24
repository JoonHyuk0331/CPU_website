package com.jbnucpu.www.controller.board;

import com.jbnucpu.www.dto.ArticleDTO;
import com.jbnucpu.www.service.SaveService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SaveController {

    private final SaveService saveService;

    public SaveController(SaveService saveService) {
        this.saveService = saveService;
    }

    @PostMapping("/save/{type}")
    public String saveArticle(@PathVariable("type") String type, ArticleDTO articleDTO) {

        System.out.println(articleDTO.getContent());

        if(type.equals("notice")){
            Boolean isSaved = saveService.processNoticeSave(articleDTO);
            System.out.println("notice c");
        }

        if(type.equals("content")) {
            Boolean isSaved = saveService.processContentSave(articleDTO);
            System.out.println("content c");
        }

        return "redirect:/"+type;
    }
}