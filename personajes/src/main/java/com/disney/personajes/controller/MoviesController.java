package com.disney.personajes.controller;

import com.disney.personajes.dto.MovieDTO;
import com.disney.personajes.service.CharactersService;
import com.disney.personajes.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    MoviesService moviesService;
    @Autowired
    CharactersService charactersService;

    //Create Movies
    @PostMapping
    private ResponseEntity<MovieDTO> createMovies(@Valid @RequestBody MovieDTO movieDTO){
        return new ResponseEntity<>(moviesService.createMovies(movieDTO), HttpStatus.CREATED);
    }

    //Get movies
    @GetMapping
    private List<MovieDTO> getMovies(@RequestParam(value = "name",required = false)String name,
                                     @RequestParam(value = "genreId",required = false)Long genreId,
                                     @RequestParam(value = "order",defaultValue = "ASC",required = false)String order){
        if (name != null){
            return moviesService.findByTitle(name);
        }
        if (genreId != null){
            return moviesService.findByGenreId(genreId);
        }
        return moviesService.getAllMovies(order);
    }

    //Update Movies
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    private ResponseEntity<MovieDTO> updateMovies(@PathVariable Long id,
                                          @Valid @RequestBody MovieDTO movieDTO) {
        MovieDTO movieResponse = moviesService.updateMovies(movieDTO,id);
        return new ResponseEntity<>(movieResponse,HttpStatus.OK);
    }

    //Delete Movies
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteMovies(@PathVariable Long id){
        moviesService.deleteMovies(id);
        return new ResponseEntity<>("Successfully removed movie",HttpStatus.OK);
    }

    //Add to Gender
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("genre/{genreId}/movie/{movieId}")
    public ResponseEntity<?> addToGender(@PathVariable (value = "genreId") Long genreId,
                                        @PathVariable (value = "movieId")Long movieId) {
        moviesService.addToGender(genreId,movieId);
        return new ResponseEntity<>("Given gender",HttpStatus.OK);
    }

    //Add Character to Movie
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("add/{movieId}/characters/{characterId}")
    private ResponseEntity<String> addCharactersToMovie(@PathVariable(value = "movieId") Long movieId,
                                                        @PathVariable(value = "characterId") Long characterId){
        moviesService.addCharacterToMovie(movieId,characterId);
        return new ResponseEntity<>("Character added successfully",HttpStatus.OK);
    }

    //Remove Character to Movie
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("delete/{movieId}/characters/{characterId}")
    private ResponseEntity<String> deleteCharactersToMovie(@PathVariable(value = "movieId") Long movieId,
                                                           @PathVariable(value = "characterId") Long characterId){
        moviesService.removeCharacterToMovie(movieId,characterId);
        return new ResponseEntity<>("Successfully removed Character",HttpStatus.OK);
    }
}
