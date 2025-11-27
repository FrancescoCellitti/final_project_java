package org.final_project_java.repository;

import org.final_project_java.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer>{
    
}
