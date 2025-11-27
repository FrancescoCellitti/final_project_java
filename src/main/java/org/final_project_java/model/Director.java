package org.final_project_java.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
@Entity
@Table(name = "directors")
public class Director {
    
    /* SEZIONE RIGHE  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "name cannot be null")
    private String name;

    @NotBlank(message = "surname cannot be null")
    private String surname;

    @NotBlank(message = "age cannot be null")
    @Positive(message = "age must be greater than 0")
    private Integer age;

    
    /* SEZIONE COLLEGAMENTI */
    @OneToMany(mappedBy = "director")
    private List<Film> films;





    /* SEZIONE GETTER E SETTER */
    public Director(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    
}
