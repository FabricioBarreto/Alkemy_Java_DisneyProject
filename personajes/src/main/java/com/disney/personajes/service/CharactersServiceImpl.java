package com.disney.personajes.service;

import com.disney.personajes.model.Characters;
import com.disney.personajes.model.Movies;
import com.disney.personajes.repository.CharactersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharactersServiceImpl implements CharactersService {

    @Autowired
    private CharactersRepository charactersRepository;
    @Autowired
    private MoviesService moviesService;

    @Override
    public Characters createCharacters(Characters characters) {
        return charactersRepository.save(characters);
    }

    @Override
    public List<Characters> getAllCharacters() {
      return charactersRepository.findAll();
    }

    @Override
    public Characters getCharactersById(Long id) {
        Optional<Characters> opt = charactersRepository.findById(id);
        if (opt.isPresent()){
            return opt.get();
        }else{
            return new Characters();
        }
    }
    @Override
    public Characters updateCharacters(Characters characters) {
        return charactersRepository.save(characters);
    }

    @Override
    public void deleteCharacters(Long id) {
        charactersRepository.deleteById(id);
    }

    @Override
    public List<Characters> getCharactersByName(String name) {
        return charactersRepository.getCharactersByName(name);
    }

    @Override
    public List<Characters> getCharacterByAge(Integer age) {
        return charactersRepository.getCharacterByAge(age);
    }
}
