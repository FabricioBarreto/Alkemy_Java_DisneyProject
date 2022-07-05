package com.disney.personajes.service.impl;

import com.disney.personajes.dto.CharacterDTO;
import com.disney.personajes.excepcions.ResourceNotFoundException;
import com.disney.personajes.model.Characters;
import com.disney.personajes.model.Movies;
import com.disney.personajes.repository.CharactersRepository;
import com.disney.personajes.repository.MoviesRepository;
import com.disney.personajes.service.CharactersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharactersServiceImpl implements CharactersService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CharactersRepository charactersRepository;

    @Autowired
    private MoviesRepository moviesRepository;

    @Override
    public CharacterDTO createCharacters(CharacterDTO characterDTO) {
        Characters characters = mapearEntidad(characterDTO);
        Characters newCharacters = charactersRepository.save(characters);
        CharacterDTO characterResponse = mapearDTO(newCharacters);
        return characterResponse;
    }

    @Override
    public List<CharacterDTO> getAllCharacters() {
        List<Characters> character = charactersRepository.findAll();
        return character.stream().map(characters -> mapearDTO(characters)).collect(Collectors.toList());
    }

    @Override
    public CharacterDTO findCharacterById(Long id) {
        Characters characters = charactersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Character", "id", id));
        return mapearDTO(characters);
    }

    @Override
    public CharacterDTO updateCharacters(CharacterDTO characterDTO, Long id) {
        Characters characters = charactersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Character", "id", id));

        characters.setImage(characterDTO.getImage());
        characters.setName(characterDTO.getName());
        characters.setAge(characterDTO.getAge());
        characters.setWeight(characterDTO.getWeight());
        characters.setHistory(characterDTO.getHistory());

        Characters characterUpdate = charactersRepository.save(characters);
        return mapearDTO(characterUpdate);
    }

    @Override
    public void deleteCharacters(Long id) {
        Characters characters = charactersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Character", "id", id));
        charactersRepository.delete(characters);
    }

    @Override
    public List<CharacterDTO> getCharactersByName(String name) {
        List<Characters> character = charactersRepository.findByName(name);
        return character.stream().map(characters -> mapearDTO(characters)).collect(Collectors.toList());
    }

    @Override
    public List<CharacterDTO> getCharacterByAge(Integer age) {
        List<Characters> character = charactersRepository.findByAge(age);
        return character.stream().map(characters -> mapearDTO(characters)).collect(Collectors.toList());
    }

    @Override
    public List<CharacterDTO> findCharactersByMovieId(Long movieId) {
        Movies movie = moviesRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", movieId));

        List<Characters> characters = charactersRepository.findByMovieId(movieId);
        return characters.stream().map(character -> mapearDTO(character)).collect(Collectors.toList());
    }

    // Convierte entidad a DTO
    private CharacterDTO mapearDTO(Characters characters) {
        CharacterDTO characterDTO = modelMapper.map(characters, CharacterDTO.class);
        return characterDTO;
    }

    // Convierte de DTO a Entidad
    private Characters mapearEntidad(CharacterDTO characterDTO) {
        Characters characters = modelMapper.map(characterDTO, Characters.class);
        return characters;
    }

}
