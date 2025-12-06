package org.final_project_java.repository;

import org.final_project_java.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Integer> {
    List<Film> findByCategories_Id(Integer categoryId);
    List<Film> findByDirector_Id(Integer directorId);
}
