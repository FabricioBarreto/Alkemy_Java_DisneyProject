package com.disney.personajes.service;

import com.disney.personajes.dto.GenreDTO;
import com.disney.personajes.dto.MovieDTO;

import java.util.List;

public interface GenreService {

    public List<GenreDTO> getAllGenre();

    public GenreDTO findGenreById(Long id);

    public GenreDTO createGenre(GenreDTO genreDTO);

    public GenreDTO updateGenre(GenreDTO genreDTO,Long genreId);

    public void deleteGenre(Long id);

}
