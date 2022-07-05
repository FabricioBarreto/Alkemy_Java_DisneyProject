package com.disney.personajes.service.impl;

import com.disney.personajes.dto.GenreDTO;
import com.disney.personajes.excepcions.ResourceNotFoundException;
import com.disney.personajes.model.Genre;
import com.disney.personajes.repository.GenreRepository;
import com.disney.personajes.service.GenreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public GenreDTO createGenre(GenreDTO genreDTO) {
        Genre genre = mapearEntidad(genreDTO);
        Genre newGenre = genreRepository.save(genre);
        GenreDTO genreResponse = mapearDTO(newGenre);
        return genreResponse;
    }

    @Override
    public List<GenreDTO> getAllGenre() {
        List<Genre> genres = genreRepository.findAll();
        return genres.stream().map(genre -> mapearDTO(genre)).collect(Collectors.toList());
    }

    @Override
    public GenreDTO updateGenre(GenreDTO genreDTO,Long id) {

        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre","id",id));

        genre.setName(genreDTO.getName());
        genre.setImage(genreDTO.getImage());

        Genre genreUpdate = genreRepository.save(genre);
        return mapearDTO(genreUpdate);
    }

    @Override
    public GenreDTO findGenreById(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre","id",id));
        return mapearDTO(genre);
    }

    @Override
    public void deleteGenre(Long id) {
        Genre genre = genreRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Genre","id",id));
        genreRepository.delete(genre);
    }

    // Convierte entidad a DTO
    private GenreDTO mapearDTO(Genre genre) {
        GenreDTO genreDTO = modelMapper.map(genre, GenreDTO.class);
        return genreDTO;
    }

    // Convierte de DTO a Entidad
    private Genre mapearEntidad(GenreDTO movieDTO) {
        Genre genre = modelMapper.map(movieDTO, Genre.class);
        return genre;
    }

}
