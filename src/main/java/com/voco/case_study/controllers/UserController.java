package com.voco.case_study.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Tag(name = "User Endpoints")
public class UserController {
    @GetMapping("/flights")
    public String index(){
        return "Hello from spring boot!";
    }

}