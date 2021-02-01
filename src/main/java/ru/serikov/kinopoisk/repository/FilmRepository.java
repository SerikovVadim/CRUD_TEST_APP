package ru.serikov.kinopoisk.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.serikov.kinopoisk.entity.Film;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film,Long> {

    List<Film> findByMovieTitle(String s);
}
