package ru.serikov.kinopoisk.entity;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Название фильма не должно быть пустым")
    private String movieTitle;
    @NotEmpty(message = "Жанр фильма должне быть заполнен")
    private String genre;
    @Min(value = 1900, message = "Год выпуска должен быть меньше 1900")
    @Max(value = 2021, message = "Год должен быть не больше 2021")
    private int productionYear;
    @Min(value = 0, message = "Бюджет не должен равен 0")
    private int filmCost;
    @Min(value = 1, message = "Рейтинг фильма не может быть меньше 1")
    @Max(value = 10, message = "Рейтинг фильма не может быть больше 10")
    private double filmRating;

    public Film() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public int getFilmCost() {
        return filmCost;
    }

    public void setFilmCost(int filmCost) {
        this.filmCost = filmCost;
    }

    public double getFilmRating() {
        return filmRating;
    }

    public void setFilmRating(double filmRating) {
        this.filmRating = filmRating;
    }
}
