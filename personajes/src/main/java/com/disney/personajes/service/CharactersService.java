package com.disney.personajes.service;

import com.disney.personajes.dto.CharacterDTO;

import java.util.List;

public interface CharactersService {

    public CharacterDTO createCharacters(CharacterDTO charactersDTO);

    public List<CharacterDTO> getAllCharacters();

    public CharacterDTO findCharacterById(Long id);

    public CharacterDTO updateCharacters(CharacterDTO characterDTO,Long id);

    public void deleteCharacters(Long id);

    public List<CharacterDTO> getCharactersByName(String name);

    public List<CharacterDTO> getCharacterByAge(Integer age);

    public List<CharacterDTO> findCharactersByMovieId(Long movieId);
}
