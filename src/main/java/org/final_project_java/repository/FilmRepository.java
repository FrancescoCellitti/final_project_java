package org.final_project_java.repository;

import org.final_project_java.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Integer> {
    List<Film> findByCategories_Id(Integer categoryId);
    List<Film> findByDirector_Id(Integer directorId);
    /* List<Film> findByCategories_Id(Integer categoryId); */

    @Modifying
    @Transactional
    @Query("update Film f set f.director = null where f.director.id = :directorId")
    int detachDirector(Integer directorId);

    @Modifying
    @Transactional
    @Query(value = "delete from films_categories where category_id = :categoryId", nativeQuery = true)
    int deleteCategoryLinks(Integer categoryId);
}
