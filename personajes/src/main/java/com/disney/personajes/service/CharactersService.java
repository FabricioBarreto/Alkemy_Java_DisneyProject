package com.disney.personajes.service;

import com.disney.personajes.model.Characters;
import com.disney.personajes.model.Movies;

import java.util.List;

public interface CharactersService {

    public Characters createCharacters(Characters characters);

    public List<Characters> getAllCharacters();

    public Characters getCharactersById(Long id);

    public Characters updateCharacters(Characters characters);

    public void deleteCharacters(Long id);

    List<Characters> getCharactersByName(String name);

    List<Characters> getCharacterByAge(Integer age);
}
