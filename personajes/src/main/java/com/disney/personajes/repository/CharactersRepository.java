package com.disney.personajes.repository;

import com.disney.personajes.model.Characters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharactersRepository extends JpaRepository<Characters, Long> {
    @Query("SELECT c FROM characters c where c.name=?1")
    List<Characters> getCharactersByName(String paramName);

    @Query("SELECT c FROM characters c where c.age=?1")
    List<Characters> getCharacterByAge(Integer age);
}
