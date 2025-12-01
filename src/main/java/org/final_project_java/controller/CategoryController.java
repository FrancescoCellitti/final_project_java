package org.final_project_java.controller;

import java.util.List;
import java.util.Optional;

import org.final_project_java.model.Category;
import org.final_project_java.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String index(Model model){
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute(categories);
        return "category/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            return "errors/404";
        }
        model.addAttribute("category", category.get());
        return "category/show";
    }

    @GetMapping("/add")
    public String add(Model model){
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
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable ("id") Integer id, Model model){
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            return "errors/404";
        }
        model.addAttribute("category", category.get());
        return "category/update";
    }

    @PostMapping("/update{id}")
    public String update(@PathVariable("id") Integer id,@Valid @ModelAttribute("category") Category formCategory,BindingResult bindingResults, Model model){
        if (bindingResults.hasErrors()) {
            return "category/update";
        }
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            return "category/update";
        }
        categoryRepository.save(formCategory);
        return "redirect:/category";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        categoryRepository.deleteById(id);
        return"redirect:/";
    }
    
}
