package org.final_project_java.controller;

import java.util.List;
import java.util.Optional;

import org.final_project_java.model.Category;
import org.final_project_java.model.Film;
import org.final_project_java.repository.CategoryRepository;
import org.final_project_java.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired private CategoryRepository categoryRepository;
    @Autowired private FilmRepository filmRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "category/index";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/add";
    }

    @PostMapping("/add")
    public String create(@Valid @ModelAttribute("category") Category formCategory,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "category/add";
        }
        categoryRepository.save(formCategory);
        return "redirect:/categories";
    }

    @GetMapping("/update/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) return "redirect:/categories";
        model.addAttribute("category", category.get());
        return "category/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id,
                         @Valid @ModelAttribute("category") Category formCategory,
                         BindingResult bindingResults){
        if (bindingResults.hasErrors()) return "category/update";
        formCategory.setId(id);
        categoryRepository.save(formCategory);
        return "redirect:/categories";
    }

    @PostMapping("/delete/{id}")
    @Transactional
    public String delete(@PathVariable("id") Integer id){
        // rimuovi i link nella tabella di join senza caricare i film
        filmRepository.deleteCategoryLinks(id);
        categoryRepository.deleteById(id);
        return "redirect:/categories";
    }
}
