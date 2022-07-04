package com.disney.personajes.controller;

import com.disney.personajes.dto.CharacterDTO;
import com.disney.personajes.service.MoviesService;
import com.disney.personajes.service.CharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharactersController {

    @Autowired
    private CharactersService charactersService;

    @Autowired
    MoviesService moviesService;

    //Create Characters
    @PostMapping
    private ResponseEntity<CharacterDTO> createCharacters(@Valid @RequestBody CharacterDTO characterDTO){
        return new ResponseEntity<>(charactersService.createCharacters(characterDTO),HttpStatus.CREATED);
    }

    //Get Characters
    @GetMapping
    private List<CharacterDTO> getAllCharacters(@RequestParam(value = "name",required = false) String name,
                                                @RequestParam(value = "age",required = false) Integer age,
                                                @RequestParam(value = "movieId",required = false) Long movieId){
        if (name != null){
            return charactersService.getCharactersByName(name);
        }
        if(age != null){
            return charactersService.getCharacterByAge(age);
        }
        if(movieId != null){
            return charactersService.findCharactersByMovieId(movieId);
        }
        return charactersService.getAllCharacters();
    }

    //Update Characters
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> updateCharacter(@PathVariable Long id,
                                                              @Valid @RequestBody CharacterDTO characterDTO){

        CharacterDTO charactersResponse = charactersService.updateCharacters(characterDTO,id);

        return new ResponseEntity<>(charactersResponse,HttpStatus.OK);
    }

    //Delete Characters
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCharacters(@PathVariable Long id){
        charactersService.deleteCharacters(id);
        return new ResponseEntity<>("character removed successfully",HttpStatus.OK);
    }
}