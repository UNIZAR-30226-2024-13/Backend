package com.proyectosoftware.backend.database.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosoftware.backend.database.entidades.Mentiroso;
import com.proyectosoftware.backend.database.services.MentirosoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
public class MentirosoController {
    
    @Autowired
    private MentirosoService mentirosoService;

    @GetMapping("getMentirosos")
    public List<Mentiroso> getAllMentiroso() {
        return mentirosoService.getAllMentiroso();
    }

    @PostMapping("addMentiroso")
    public Mentiroso saveMentiroso(@RequestBody Mentiroso mentiroso) {
        return mentirosoService.saveMentiroso(mentiroso);
    }
    
    @GetMapping("getMentiroso")
    public Optional<Mentiroso> getMentiroso(@RequestParam String idMentiroso) {
        return mentirosoService.getMentiroso(idMentiroso);
    }
}