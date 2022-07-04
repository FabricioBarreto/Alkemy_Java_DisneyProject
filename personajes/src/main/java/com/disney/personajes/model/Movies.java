package com.disney.personajes.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private Long genreId;

    @JoinTable(name = "movies_characters",joinColumns = @JoinColumn(name = "character_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id",referencedColumnName = "id"))
    @ManyToMany(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.LAZY)
    private Set<Characters> characters = new HashSet<>();

    public void addCharacter(Characters character) {
        characters.add(character);
    }

}
