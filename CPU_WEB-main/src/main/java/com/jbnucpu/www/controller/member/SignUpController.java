package com.jbnucpu.www.controller.member;

import com.jbnucpu.www.dto.SignUpDTO;
import com.jbnucpu.www.service.SignUpService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

    private final SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {

        this.signUpService = signUpService;
    }

    @GetMapping("/signup")
    public String signUp() {

        return "signup";
    }

    @PostMapping("/signup")
    public String processSignUp(SignUpDTO signUpDTO) {

        Boolean isSigned = signUpService.processSignUp(signUpDTO);

        return "signup";
    }
}
