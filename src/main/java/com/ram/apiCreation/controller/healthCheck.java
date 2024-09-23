package com.ram.apiCreation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthCheck {
    @GetMapping("/check")
    public String health(){
        return "ok";
    }

    @PostMapping("/insert")
    public void updateDb(){

    }
}
