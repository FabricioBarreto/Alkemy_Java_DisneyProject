package com.disney.personajes.repository;

import com.disney.personajes.model.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoviesRepository extends JpaRepository<Movies,Long> {
    List<Movies> findByTitle(String title);

    List<Movies> findByGenreId(Long genreId);
}
