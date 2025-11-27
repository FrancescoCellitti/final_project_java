package org.final_project_java.controller;

import java.util.List;
import java.util.Optional;

import org.final_project_java.model.Film;
import org.final_project_java.repository.CategoryRepository;
import org.final_project_java.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/films")
public class FilmRestController {
    @Autowired
    private FilmRepository filmRepository;
    private CategoryRepository categoryRepository;

    @GetMapping
    public String index(Model model) {
        List<Film> films = filmRepository.findAll();
        model.addAttribute("films", films);
        return "film/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Optional<Film> films = filmRepository.findById(id);
        if (films.isEmpty()) {
            return "/errors/404";
        }

        model.addAttribute("films", films.get());
        return "film/show";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("film", new Film());
        model.addAttribute("catgories", categoryRepository.findAll());
        return "film/add";
    }

    @PostMapping("/add")
    public String create(@Valid @ModelAttribute("film") Film formFilm, BindingResult bindingResult, @RequestParam(value = "category", required= false) List<Integer> category, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("film", formFilm);
            model.addAttribute("category", categoryRepository.findAll());
            return "/add";
        }
      filmRepository.save(formFilm);
      return "redirect/:";        
    } 
}
