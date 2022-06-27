package com.disney.personajes.controller;

import com.disney.personajes.model.Characters;
import com.disney.personajes.model.Movies;
import com.disney.personajes.service.CharactersService;
import com.disney.personajes.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/movies")
@RestController
public class MoviesController {

    @Autowired
    MoviesService moviesService;
    @Autowired
    CharactersService charactersService;

    @GetMapping
    private ResponseEntity<?> getMovies(
            @RequestParam(required = false)String title ,
            @RequestParam(required = false)Long idGenre){

        if (title != null){
            return new ResponseEntity<>(moviesService.findByTitle(title), HttpStatus.OK);
        }
        return new ResponseEntity<>(moviesService.getAllMovies(), HttpStatus.OK);
    }

    //Obtener Movies
    @GetMapping("/{id}")
    private ResponseEntity<?> getMoviesById(@PathVariable Long id){
        return new ResponseEntity<>(moviesService.getMoviesById(id),HttpStatus.OK);
    }

    //Crear Movies
    @PostMapping
    private ResponseEntity<?> createMovies(@RequestBody @Valid Movies movies){
        return new ResponseEntity<>(moviesService.createMovies(movies), HttpStatus.CREATED);
    }

    //Modificar Movies
    @PutMapping("/update/{id}")
    private ResponseEntity<?> updateMovies(@PathVariable Long id,
                                          @RequestBody Movies dataUpdated) {
        Movies movies = moviesService.getMoviesById(id);
        movies.setTitle(dataUpdated.getTitle());
        movies.setImage(dataUpdated.getImage());
        movies.setCreation(dataUpdated.getCreation());
        movies.setQualification(dataUpdated.getQualification());
        return new ResponseEntity<>(moviesService.updateMovies(movies),HttpStatus.OK);
    }

    //Eliminar Movies
    @DeleteMapping("/delete/{id}")
    private void deleteMovies(@PathVariable Long id){
        moviesService.deleteMovies(id);
    }

    //Agregar Character a Movies
    @PostMapping("/{idMovie}/characters/{idCharacters}")
    private void addCharacters(@PathVariable Long idMovie,
                               @PathVariable Long idCharacters){
        Movies movie = moviesService.getMoviesById(idMovie);
        Characters characters = charactersService.getCharactersById(idCharacters);
        movie.addCharacter(characters);
        charactersService.updateCharacters(characters);
        moviesService.updateMovies(movie);
    }
}
