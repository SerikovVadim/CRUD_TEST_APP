package ru.serikov.kinopoisk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.serikov.kinopoisk.entity.Actor;

public interface ActorRepository extends JpaRepository<Actor,Long> {
}
