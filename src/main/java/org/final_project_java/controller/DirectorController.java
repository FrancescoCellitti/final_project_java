package org.final_project_java.controller;

import org.final_project_java.model.Director;
import org.final_project_java.model.Film;
import org.final_project_java.repository.DirectorRepository;
import org.final_project_java.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/director")
public class DirectorController {

    @Autowired private DirectorRepository directorRepository;
    @Autowired private FilmRepository filmRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("directors", directorRepository.findAll());
        return "director/index";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("director", new Director());
        return "director/add";
    }

    @PostMapping("/add")
    public String create(@Valid @ModelAttribute("director") Director director, BindingResult result) {
        if (result.hasErrors()) return "director/add";
        directorRepository.save(director);
        return "redirect:/director";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {
        var d = directorRepository.findById(id).orElse(null);
        if (d == null) return "redirect:/director";
        model.addAttribute("director", d);
        return "director/update";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Integer id,
                         @Valid @ModelAttribute("director") Director director,
                         BindingResult result) {
        if (result.hasErrors()) return "director/update";
        director.setId(id);
        directorRepository.save(director);
        return "redirect:/director";
    }

    @PostMapping("/{id}/delete")
    @Transactional
    public String delete(@PathVariable Integer id) {
        var d = directorRepository.findById(id).orElse(null);
        if (d == null) return "redirect:/director";
        // stacca dai film prima di cancellare (serve director_id nullable in DB)
        List<Film> films = filmRepository.findByDirector_Id(id);
        for (Film f : films) {
            f.setDirector(null);
            filmRepository.save(f);
        }
        directorRepository.delete(d);
        return "redirect:/director";
    }
}
