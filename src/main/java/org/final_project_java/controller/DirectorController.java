package org.final_project_java.controller;

import java.util.List;
import java.util.Optional;

import org.final_project_java.model.Category;
import org.final_project_java.model.Director;
import org.final_project_java.repository.DirectorRepository;
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
@RequestMapping("/director")
public class DirectorController {
    @Autowired
    private DirectorRepository repository;

    @GetMapping
    public String index(Model model){
        List<Director> director = repository.findAll();
        model.addAttribute(director);
        return "director/index";
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") Integer id, Model model){
        Optional<Director> director = repository.findById(id);
        if (director.isEmpty()) {
            return "errors/404";
        }        
        model.addAttribute(director);
        return "director/show";
    }

    @GetMapping("add")
    public String add(Model model){
        model.addAttribute("category", new Category());
        return "category/add";
    }

    @PostMapping("/add")
    public String create(@Valid @ModelAttribute("Director") Director formDirector, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "director/add";
        }
        repository.save(formDirector);
        return "redirect:/director";
    }

    @GetMapping("/update{id}")
    public String update(@PathVariable("id") Integer id, Model model){
        Optional<Director> director = repository.findById(id);
        if(director.isEmpty()){
            return "errors/404";
        }
        model.addAttribute("director", director);
        return "redirect:/";
    }

    @PostMapping("/update{id}")
    public String update(@Valid @PathVariable("id") Integer id, @ModelAttribute("director") Director formDirector, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "/director/update";
        }
        Optional<Director> director = repository.findById(id);
        if (director.isEmpty()) {
            return "errors/404";
        }
        repository.save(formDirector);
        return "redirect:/director";
    }

    @PostMapping("/delete{id}")
    public String delete(@PathVariable("id") Integer id){
        repository.deleteById(id);
        return "redirect:/directors";
    }

}
