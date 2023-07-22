package com.phiwer.utopia.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/billybut")
    String getBillyBut() {
        return "Hello World";

    }
}
