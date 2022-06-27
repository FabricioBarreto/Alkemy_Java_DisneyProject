package com.disney.personajes.service;

import com.disney.personajes.model.Genre;

import java.util.List;

public interface GenreService {

    public List<Genre> getAllGenre();

    public Genre getGenreById(Long id);

    public Genre createGenre(Genre genre);

    public Genre updateGenre(Genre genre);

    public void deleteGenre(Long id);

}
