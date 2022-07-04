package com.disney.personajes.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Table(name = "characters")
@Entity(name = "characters")
public class Characters {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String image;

    private String name;

    private Integer age;

    private Float weight;

    private String history;

    private Long movieId;

    @JsonIgnore
    @ManyToMany(mappedBy = "characters")
    private List<Movies> movies = new ArrayList<>();

}