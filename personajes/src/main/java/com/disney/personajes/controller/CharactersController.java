package com.disney.personajes.controller;

import com.disney.personajes.model.Characters;
import com.disney.personajes.service.MoviesService;
import com.disney.personajes.service.CharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/characters")
public class CharactersController {

    @Autowired
    private CharactersService charactersService;

    @Autowired
    MoviesService moviesService;

    public CharactersController(CharactersService charactersService, MoviesService moviesService){
        this.charactersService = charactersService;
        this.moviesService = moviesService;
    }

    //Obtener personajes
    @GetMapping
    private ResponseEntity<?> getAllCharacters(@RequestParam(required = false) String name,
                                               @RequestParam(required = false) Integer age,
                                               @RequestParam(required = false) Long idMovie){
        if (name != null){
            return new ResponseEntity<>(charactersService.getCharactersByName(name), HttpStatus.OK);
        }else if(age != null){
            return new ResponseEntity<>(charactersService.getCharacterByAge(age), HttpStatus.OK);
        }
        return new ResponseEntity<>(charactersService.getAllCharacters(), HttpStatus.OK);
    }

    //Crear personajes
    @PostMapping
    private ResponseEntity<?> createCharacters(@RequestBody Characters characters){
        return new ResponseEntity<>(charactersService.createCharacters(characters),HttpStatus.CREATED);
    }

    //Modificar personajes
    @PutMapping("/{id}")
    private ResponseEntity<?> updateCharacters(@PathVariable Long id,
                                           @RequestBody Characters dataUpdated) {
        Characters characters = charactersService.getCharactersById(id);
        characters.setImage(dataUpdated.getImage());
        characters.setName(dataUpdated.getName());
        characters.setAge(dataUpdated.getAge());
        characters.setWeight(dataUpdated.getWeight());
        characters.setHistory(dataUpdated.getHistory());
        return new ResponseEntity<>(charactersService.updateCharacters(characters),HttpStatus.OK);
    }

    //Eliminar Characters
    @DeleteMapping("/{id}")
    private void deleteCharacters(@PathVariable Long id){
        charactersService.deleteCharacters(id);
    }
}