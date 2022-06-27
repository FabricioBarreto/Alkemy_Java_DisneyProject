package com.disney.personajes.repository;

import com.disney.personajes.model.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movies,Long> {

    List<Movies> findByTitle(String title);

}
