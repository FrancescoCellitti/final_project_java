package org.final_project_java.controller;

import java.util.List;
import java.util.Optional;

import org.final_project_java.model.Director;
import org.final_project_java.model.Film;
import org.final_project_java.repository.CategoryRepository;
import org.final_project_java.repository.DirectorRepository;
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
public class FilmController {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private DirectorRepository directorRepository;

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
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("directors", directorRepository.findAll());
        return "film/add";
    }

    @PostMapping("/add")
    public String create(@Valid @ModelAttribute("film") Film formFilm,
                         BindingResult bindingResult,
                         @RequestParam(value = "category", required= false) List<Integer> categoryIds, @RequestParam(value = "director", required = true) Integer director,
                         Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("director", directorRepository.findAll());
            return "film/add";
        }
        formFilm.setCategories(categoryIds != null
                ? categoryRepository.findAllById(categoryIds)
                : List.of());
       Optional<Director> dirOpt = directorRepository.findById(director);
        if (dirOpt.isEmpty()) {
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("director", directorRepository.findAll());
            model.addAttribute("directorError", "Seleziona un regista valido.");
            return "film/add";
        }
        formFilm.setDirector(dirOpt.get());
        filmRepository.save(formFilm);
        return "redirect:/films";
    } 


    @GetMapping("/update/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Optional<Film> film = filmRepository.findById(id);
        if (film.isEmpty()) {
            return "errors/404";
        }
        model.addAttribute("film", film.get());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("directors", directorRepository.findAll());
        return "film/update";
    }

   @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id,
                     @Valid @ModelAttribute("film") Film formFilm,
                     BindingResult bindingResult,
                     @RequestParam(value = "category", required = false) List<Integer> categoryIds,
                     @RequestParam(value = "director", required = true) Integer directorId,
                     Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("directors", directorRepository.findAll());
            return "film/update";
        }
        formFilm.setId(id);
        formFilm.setCategories(categoryIds != null
                ? categoryRepository.findAllById(categoryIds)
                : List.of());

        Optional<Director> dirOpt = directorRepository.findById(directorId);
        if (dirOpt.isEmpty()) {
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("directors", directorRepository.findAll());
            model.addAttribute("directorError", "Seleziona un regista valido.");
            return "film/update";
        }
        formFilm.setDirector(dirOpt.get());

        filmRepository.save(formFilm);
        return "redirect:/films";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        filmRepository.deleteById(id);
        return "redirect:/";
    }

}
