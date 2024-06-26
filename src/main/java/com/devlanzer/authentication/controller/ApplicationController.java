package com.devlanzer.authentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/app")
public class ApplicationController {

    @GetMapping("/get-hello")
    public String getHello() {
        return "Hello User";
    }
}
