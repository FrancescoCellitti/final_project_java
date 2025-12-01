package org.final_project_java.controller;

import java.util.List;


import org.final_project_java.model.Director;
import org.final_project_java.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/director")
public class DirectorRestController {
    @Autowired
    private DirectorRepository repository;

    @GetMapping
    public List<Director> directors(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Director> directorDetails(@PathVariable("id") Integer id){
        return repository.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(()-> ResponseEntity.notFound().build());
    }
}

