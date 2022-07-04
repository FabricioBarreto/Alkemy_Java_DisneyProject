package com.disney.personajes.service;

import com.disney.personajes.dto.MovieDTO;

import java.util.List;

public interface MoviesService {

    public List<MovieDTO> getAllMovies(String order);

    public MovieDTO findMovieById(Long id);

    public MovieDTO createMovies(MovieDTO movieDTO);

    public MovieDTO updateMovies(MovieDTO movieDTO,Long movieId);

    public void deleteMovies(Long id);

    List<MovieDTO> findByTitle(String title);

    public void addToGender(Long genreId,Long movieId);

    public void addCharacterToMovie(Long movieId,Long characterId);

    public void removeCharacterToMovie(Long movieId,Long characterId);

    public List<MovieDTO> findByGenreId(Long genreId);
}
