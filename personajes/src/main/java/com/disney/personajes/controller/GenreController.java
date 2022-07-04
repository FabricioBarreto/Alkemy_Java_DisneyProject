package com.disney.personajes.controller;

import com.disney.personajes.dto.GenreDTO;
import javax.validation.Valid;

import com.disney.personajes.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreController {

    @Autowired
    GenreService genreService;

    //Create Genre
    @PostMapping
    private ResponseEntity<GenreDTO> createGenre(@Valid @RequestBody GenreDTO genreDTO){
        return new ResponseEntity<>(genreService.createGenre(genreDTO), HttpStatus.CREATED);
    }

    //Get Genre
    @GetMapping
    private List<GenreDTO> getAllGenre(){
        return genreService.getAllGenre();
    }

    //Update Genre
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    private ResponseEntity<GenreDTO> updateGenre(@PathVariable Long id,
                                                 @Valid @RequestBody GenreDTO genreDTO) {
        GenreDTO genreResponse = genreService.updateGenre(genreDTO,id);

        return new ResponseEntity<>(genreResponse,HttpStatus.OK);
    }

    //Delete Genre
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteGenre(@PathVariable Long id){
        genreService.deleteGenre(id);
        return new ResponseEntity<>("Successfully removed genre",HttpStatus.OK);
    }

}
