package org.final_project_java.controller;

import java.util.List;

import org.final_project_java.model.Film;
import org.final_project_java.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/films")
@CrossOrigin(origins = "http://localhost:5173")
public class FilmRestController {
    @Autowired
    private FilmRepository filmRepository;

    @GetMapping
    public List<Film> films(){

        return filmRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> filmDetails(@PathVariable Integer id){
        return filmRepository.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(()-> ResponseEntity.notFound().build());
    }
}
