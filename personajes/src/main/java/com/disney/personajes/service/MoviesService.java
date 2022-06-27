package com.disney.personajes.service;

import com.disney.personajes.model.Movies;

import java.util.List;

public interface MoviesService {

    public List<Movies> getAllMovies();

    public Movies getMoviesById(Long id);

    public Movies createMovies(Movies movies);

    public Movies updateMovies(Movies movies);

    public void deleteMovies(Long id);

    List<Movies> findByTitle(String title);

}
