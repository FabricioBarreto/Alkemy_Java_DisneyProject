package com.disney.personajes.dto;

import com.disney.personajes.model.Movies;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
public class GenreDTO {

    private Long id;

    @NotEmpty(message = "the name field cannot be empty")
    private String name;

    private String image;

    private List<Movies> movies = new ArrayList<>();

}
