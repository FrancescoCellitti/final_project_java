package org.final_project_java.model;



import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "films")
public class Film {

    /* SEZIONE RIGHE */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il titolo è obbligatorio")
    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    private String description;

    @NotNull(message = "La durata è obbligatoria")
    @Min(value = 1, message = "La durata deve essere almeno 1 minuto")
    @Max(value = 600, message = "La durata non può superare 600 minuti")
    @Column(name = "duration_time")
    private Integer duration_time;

    @NotNull(message = "L'anno di uscita è obbligatorio")
    @Min(value = 1888, message = "L'anno deve essere ≥ 1888")
    @Max(value = 2100, message = "L'anno deve essere ≤ 2100")
    @Column(name = "release_year")
    private Integer release_year;

    /* SEZIONE COLLEGAMENTI */
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id", nullable = true)
    private Director director;

    @ManyToMany
    @JoinTable(
        name = "films_categories",
        joinColumns =  @JoinColumn(name = "film_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private java.util.List<Category> categories;

    /* SEZIONE GETTER E SETTER */
    public Film(){

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration_time() {
        return duration_time;
    }

    public void setDuration_time(Integer duration_time) {
        this.duration_time = duration_time;
    }

    public Integer getRelease_year() {
        return release_year;
    }

    public void setRelease_year(Integer release_year) {
        this.release_year = release_year;
    }


    public java.util.List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }    
    

}
