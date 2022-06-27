package com.disney.personajes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "movies")
@Table(name = "movies")
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private String title;

    private LocalDate creation;

    private Integer qualification;

    @JsonIgnore
    @ManyToMany(cascade = {
        CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(
            name = "movies_characters",
            joinColumns = {@JoinColumn(name = "fk_movies",nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "fk_characters",nullable = false)}
    )
    private List<Characters> characters = new ArrayList<Characters>();

    public void addCharacter(Characters character){
        characters.add(character);
        character.getMovies().add(this);
    }

    public void putCharacters(Characters character){
        characters.remove(character);
        character.getMovies().remove(this);
    }

}
