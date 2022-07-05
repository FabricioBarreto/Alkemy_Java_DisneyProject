package com.disney.personajes.repository;

import com.disney.personajes.model.Characters;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharactersRepository extends JpaRepository<Characters, Long> {

    public List<Characters> findByName(String name);

    public List<Characters> findByAge(Integer age);

    public List<Characters> findByMovieId(Long movieId);

}
