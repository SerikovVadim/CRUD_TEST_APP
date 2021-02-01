package ru.serikov.kinopoisk.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.serikov.kinopoisk.entity.Film;
import ru.serikov.kinopoisk.service.FilmService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/films")
public class FilmsController {

    private final FilmService filmService;

    @Autowired
    public FilmsController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public String findAll(Model model){
        List<Film> films = filmService.findByAll();
        model.addAttribute("films", films);
        model.addAttribute("pageTitle","Список фильмов");
        return "film/show";
    }

    @GetMapping("/add")
    public String addFilmForm(@ModelAttribute("film") Film film,
                              Model model){
        model.addAttribute("pageTitle", "Добавление фильма в базу");
        return "film/add";
    }

    @PostMapping("/add")
    public String addFilm(@ModelAttribute("film") @Valid Film film,
                          BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "film/add";
        }
        filmService.save(film);
        return "redirect:/films";

    }

    @GetMapping("/{id}")
    public String details(@PathVariable("id") long id, Model model){
        if(!filmService.existsById(id)){
            return "redirect:/films";
        }
        Optional<Film> film = filmService.findById(id);
        List<Film> list = new ArrayList<>();
        film.ifPresent(list::add);
        model.addAttribute("film", list);
        model.addAttribute("pageTitle", "Подробнее");
        return "film/details";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") long id, Model model){
        if(!filmService.existsById(id)){
            return "redirect:/films";
        }
        Film film = filmService.findById(id).orElseThrow(AssertionError::new);
        model.addAttribute("film", film);
        model.addAttribute("pageTitle", "Редактировать фильм");
        return "film/edit";
    }

    @PostMapping("/{id}/edit")
    public String edit(@ModelAttribute("film") @Valid Film film, @PathVariable("id") long id,
                       BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "film/edit";
        }
        filmService.save(film);
        return "redirect:/films";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") long id){
        filmService.delete(id);
        return "redirect:/films";
    }

    @GetMapping("/index")
    public String movieSearch(@RequestParam("movieTitle") String movieTitle, Model model){
        List<Film> list = new ArrayList<>(filmService.findByMovieTitle(movieTitle));
        model.addAttribute("pageTitle", "Поиск фильма по названию");
        model.addAttribute("films", list);
        return "film/movieSearch";
    }

    @GetMapping("/top5")
    public String top5Films(Model model){
        List<Film> list =filmService.findByAll();
        List<Film> films = list.stream().limit(5).sorted((a,b)-> Double.compare(b.getFilmRating(), a.getFilmRating())
        ).collect(Collectors.toList());
       model.addAttribute("films", films);
       model.addAttribute("pageTitle", "Топ 5 фильмов");
       return "/film/top5Film";
    }


}
