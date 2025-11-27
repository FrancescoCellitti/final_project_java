package org.final_project_java.model;

import java.util.List;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "categories")
public class Category {

    /* SEZIONE RIGHE */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "title cannot be null")
    @Column(name =  "title", nullable = false)
    private String title;

   

    /* SEZIONE COLLEGAMENTI */
    @ManyToMany(mappedBy = "categories")
    List<Film> films;




    /* SEZIONE GETTER E SETTERS */
     public Category(){

    }
    
    public List<Film> getFilms() {
        return films;
    }

     public void setFilms(List<Film> films) {
         this.films = films;
     }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    
}
