package com.jbnucpu.www.controller.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoticeController {
    @GetMapping("/notice")
    public String notice() {

        return "notice";
    }
}
