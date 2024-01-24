package com.jbnucpu.www.controller.study;

import com.jbnucpu.www.service.StudyEnrollService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudyEnrollController {

    private final StudyEnrollService studyEnrollService;

    public StudyEnrollController(StudyEnrollService studyEnrollService){
        this.studyEnrollService = studyEnrollService;
    }


}
