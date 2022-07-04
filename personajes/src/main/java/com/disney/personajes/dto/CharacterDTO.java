package com.disney.personajes.dto;

import com.disney.personajes.model.Movies;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
public class CharacterDTO {

    private Long id;

    private String image;

    @NotEmpty(message = "The name field cannot be empty")
    private String name;

    private Integer age;

    private  float weight;

    private String history;

    private Long movieId;

    private List<Movies> movies = new ArrayList<>();
}
