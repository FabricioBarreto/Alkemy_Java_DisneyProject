package com.disney.personajes.controller;

import com.disney.personajes.model.Genre;
import javax.validation.Valid;
import com.disney.personajes.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genre")
public class GenreController {

    @Autowired
    GenreService genreService;

    @GetMapping
    private ResponseEntity<?> getAllGenre(){
        return new ResponseEntity<>(genreService.getAllGenre(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getGenreById(@PathVariable Long id){
        return new ResponseEntity<>(genreService.getGenreById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    private ResponseEntity<?> createGenre(@RequestBody @Valid Genre genre){
        return new ResponseEntity<>(genreService.createGenre(genre), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<?> updateGenre(@PathVariable Long id,
            @RequestBody Genre dataUpdated) {
        Genre genre = genreService.getGenreById(id);
        genre.setName(dataUpdated.getName());
        genre.setImage(dataUpdated.getImage());
        return new ResponseEntity<>(genreService.updateGenre(genre),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    private void deleteGenre(@PathVariable Long id){
        genreService.deleteGenre(id);
    }

}
