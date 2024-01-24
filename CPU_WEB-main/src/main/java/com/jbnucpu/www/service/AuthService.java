package com.jbnucpu.www.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    public String getUsername(){

        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
