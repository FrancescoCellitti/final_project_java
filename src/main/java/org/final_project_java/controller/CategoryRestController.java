package org.final_project_java.controller;

import java.util.List;
import org.final_project_java.model.Category;
import org.final_project_java.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {
    @Autowired
    private CategoryRepository repository;

    @GetMapping
    public List<Category> categories(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> categoryDetails(@PathVariable("id") Integer id){
        return repository.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
