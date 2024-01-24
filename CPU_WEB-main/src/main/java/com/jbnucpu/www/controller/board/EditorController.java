package com.jbnucpu.www.controller.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EditorController {

    @GetMapping("/editor/notice")
    public String noticeEditor(Model model){

        model.addAttribute("type", "notice");

        return "editor";
    }

    @GetMapping("/editor/notice/{no}")
    public String noticeUpdateEditor(@PathVariable("no")Long no, Model model){

        model.addAttribute("type", "notice");

        return "editor";
    }

    @GetMapping("/editor/content")
    public String contentEditor(Model model){

        model.addAttribute("type", "content");

        return "editor";
    }

    @GetMapping("/editor/content/{no}")
    public String contentUpdateEditor(@PathVariable("no")Long no, Model model){

        model.addAttribute("type", "content");

        return "editor";
    }
}
