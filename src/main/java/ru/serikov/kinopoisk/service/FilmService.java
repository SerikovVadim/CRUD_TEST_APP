package ru.serikov.kinopoisk.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.serikov.kinopoisk.entity.Film;
import ru.serikov.kinopoisk.repository.FilmRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FilmService {


    private final FilmRepository filmRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public Optional<Film> findById(long id){
      return   filmRepository.findById(id);
    }

    public boolean existsById(long id){
      return   filmRepository.existsById(id);
    }

    public List<Film> findByAll(){
        return filmRepository.findAll();
    }

    public void save(Film film){
        filmRepository.save(film);
    }

    public void delete(long id){
        filmRepository.deleteById(id);
    }

    public List<Film> findByMovieTitle(String movieTitle){
        return filmRepository.findByMovieTitle(movieTitle);
    }
}
