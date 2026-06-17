package com.voco.case_study.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/role")
public class RoleTestController {


    @GetMapping("/all")
    public String allAccess() {
        return "Burası genel duyuru panosu. Token'ı olan herkes görebilir.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('PASSENGER') or hasRole('ADMIN')")
    public String userAccess() {
        return "Burası KULLANICI paneli. Sadece User ve Admin'ler görebilir.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Burası GİZLİ ADMIN paneli. Hoş geldin patron!";
    }
}