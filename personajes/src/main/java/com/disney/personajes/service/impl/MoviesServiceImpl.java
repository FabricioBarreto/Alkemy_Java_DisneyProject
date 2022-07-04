package com.disney.personajes.service.impl;

import com.disney.personajes.dto.CharacterDTO;
import com.disney.personajes.dto.MovieDTO;
import com.disney.personajes.exeptions.ResourceNotFoundException;
import com.disney.personajes.model.Characters;
import com.disney.personajes.model.Genre;
import com.disney.personajes.model.Movies;
import com.disney.personajes.repository.CharactersRepository;
import com.disney.personajes.repository.GenreRepository;
import com.disney.personajes.repository.MoviesRepository;
import com.disney.personajes.service.MoviesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoviesServiceImpl implements MoviesService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private CharactersRepository charactersRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public MovieDTO createMovies(MovieDTO movieDTO) {
        Movies movies = mapearEntidad(movieDTO);
        Movies newMovie = moviesRepository.save(movies);
        MovieDTO movieResponse = mapearDTO(newMovie);
        return movieResponse;
    }

    @Override
    public List<MovieDTO> getAllMovies(String order) {
        List<Movies> movies = moviesRepository.findAll();

        String orderByField = "titulo";

        Sort sort = order.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderByField).ascending()
                : Sort.by(orderByField).descending();

        return movies.stream().map(movie -> mapearDTO(movie)).collect(Collectors.toList());
    }

    @Override
    public MovieDTO findMovieById(Long id) {
        Movies movie = moviesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));
        return mapearDTO(movie);
    }

    @Override
    public MovieDTO updateMovies(MovieDTO movieDTO,Long id) {
        Movies movie = moviesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));

        movie.setImage(movieDTO.getImage());
        movie.setTitle(movieDTO.getTitle());
        movie.setCreation(movieDTO.getCreation());
        movie.setQualification(movieDTO.getQualification());

        Movies movieUpdate = moviesRepository.save(movie);
        return mapearDTO(movieUpdate);
    }

    @Override
    public void deleteMovies(Long id) {
        Movies movies = moviesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));
        moviesRepository.delete(movies);
    }

    @Override
    public List<MovieDTO> findByTitle(String title) {
        List<Movies> movies = moviesRepository.findByTitle(title);
        return movies.stream().map(movie -> mapearDTO(movie)).collect(Collectors.toList());
    }

    @Override
    public void addToGender(Long genreId, Long movieId) {

        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new ResourceNotFoundException("Genre", "id", genreId));

        Movies movie = moviesRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", movieId));

        genre.getMovies().add(movie);

        genreRepository.save(genre);

        movie.setGenreId(genreId);

        moviesRepository.save(movie);
    }

    @Override
    public void addCharacterToMovie(Long movieId, Long characterId) {
        Characters characters = charactersRepository.findById(characterId)
                .orElseThrow(() -> new ResourceNotFoundException("Character", "id", characterId));

        Movies movie = moviesRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", movieId));

        characters.setMovieId(movieId);

        movie.addCharacter(characters);

        moviesRepository.save(movie);
    }

    @Override
    public void removeCharacterToMovie(Long movieId, Long characterId) {
        Characters characters = charactersRepository.findById(characterId)
                .orElseThrow(() -> new ResourceNotFoundException("Character", "id", characterId));

        Movies movie = moviesRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", movieId));

        movie.getCharacters().removeIf(c -> c.equals(characters));

        moviesRepository.save(movie);
    }

    @Override
    public List<MovieDTO> findByGenreId(Long genreId) {
        List<Movies> movies = moviesRepository.findByGenreId(genreId);
        return movies.stream().map(movie -> mapearDTO(movie)).collect(Collectors.toList());
    }

    // Convierte entidad a DTO
    private MovieDTO mapearDTO(Movies movie) {
        MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
        return movieDTO;
    }

    // Convierte de DTO a Entidad
    private Movies mapearEntidad(MovieDTO movieDTO) {
        Movies movie = modelMapper.map(movieDTO, Movies.class);
        return movie;
    }

}
