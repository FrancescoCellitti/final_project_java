package org.final_project_java.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.final_project_java.model.Category;
import org.final_project_java.model.Director;
import org.final_project_java.model.Film;
import org.final_project_java.repository.CategoryRepository;
import org.final_project_java.repository.DirectorRepository;
import org.final_project_java.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private DirectorRepository directorRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("film", new Film());
        model.addAttribute("directors", directorRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "film/add";
    }

    @PostMapping("/add")
    public String create(@Valid @ModelAttribute("film") Film film, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("directors", directorRepository.findAll());
            model.addAttribute("categories", categoryRepository.findAll());
            return "film/add";
        }

        // Risolvi Director (senza lambda)
        if (film.getDirector() != null && film.getDirector().getId() != null) {
            Optional<Director> dirOpt = directorRepository.findById(film.getDirector().getId());
            if (dirOpt.isPresent()) {
                film.setDirector(dirOpt.get());
            } else {
                model.addAttribute("directors", directorRepository.findAll());
                model.addAttribute("categories", categoryRepository.findAll());
                model.addAttribute("directorError", "Seleziona un regista valido.");
                return "film/add";
            }
        } else {
            model.addAttribute("directors", directorRepository.findAll());
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("directorError", "Seleziona un regista valido.");
            return "film/add";
        }

        // Risolvi Categories (senza stream)
        if (film.getCategories() != null && !film.getCategories().isEmpty()) {
            List<Category> resolved = new ArrayList<Category>();
            for (Category ref : film.getCategories()) {
                if (ref != null && ref.getId() != null) {
                    Optional<Category> cOpt = categoryRepository.findById(ref.getId());
                    if (cOpt.isPresent()) {
                        resolved.add(cOpt.get());
                    }
                }
            }
            film.setCategories(resolved);
        } else {
            film.setCategories(new ArrayList<Category>());
        }

        filmRepository.save(film);
        return "redirect:/films";
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("films", filmRepository.findAll());
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
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("film") Film film,
                     BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("directors", directorRepository.findAll());
            model.addAttribute("categories", categoryRepository.findAll());
            return "film/update";
        }
        film.setId(id);

        // Risolvi Director (senza lambda)
        if (film.getDirector() != null && film.getDirector().getId() != null) {
            Optional<Director> dirOpt = directorRepository.findById(film.getDirector().getId());
            if (dirOpt.isPresent()) {
                film.setDirector(dirOpt.get());
            } else {
                model.addAttribute("directors", directorRepository.findAll());
                model.addAttribute("categories", categoryRepository.findAll());
                model.addAttribute("directorError", "Seleziona un regista valido.");
                return "film/update";
            }
        } else {
            model.addAttribute("directors", directorRepository.findAll());
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("directorError", "Seleziona un regista valido.");
            return "film/update";
        }

        // Risolvi Categories (senza stream)
        if (film.getCategories() != null && !film.getCategories().isEmpty()) {
            List<Category> resolved = new ArrayList<Category>();
            for (Category ref : film.getCategories()) {
                if (ref != null && ref.getId() != null) {
                    Optional<Category> cOpt = categoryRepository.findById(ref.getId());
                    if (cOpt.isPresent()) {
                        resolved.add(cOpt.get());
                    }
                }
            }
            film.setCategories(resolved);
        } else {
            film.setCategories(new ArrayList<Category>());
        }

        filmRepository.save(film);
        return "redirect:/films";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        filmRepository.deleteById(id);
        return "redirect:/films";
    }

}
