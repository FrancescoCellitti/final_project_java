package org.final_project_java.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "films")
public class Film {

    /* SEZIONE RIGHE */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "title cannot be null")
    @Column(name = "title", nullable = false)
    private String title;


    @Lob
    private String description;

    @NotBlank(message = "duration cannot be null")
    @Positive(message = "duration_time must be greater than 0")
    @Column(name = "duration_time", nullable = false)
    private Integer duration_time;

    @Min(value = 1888, message = "release_year must be >= 1888")
    @Max(value = 2100, message = "release_year must be <= 2100")
    @Column(name = "release_year")
    private Integer release_year;

    /* SEZIONE COLLEGAMENTI */
    @ManyToOne
    @JoinColumn(name = "director_id", nullable = false)
    private Film film;
    






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
    
    

}
