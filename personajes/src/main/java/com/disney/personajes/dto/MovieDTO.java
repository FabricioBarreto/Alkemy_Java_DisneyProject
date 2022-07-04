package com.disney.personajes.dto;

import com.disney.personajes.model.Characters;
import com.disney.personajes.model.Genre;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class MovieDTO {

    private Long id;

    private String image;

    @NotEmpty(message = "The title field cannot be empty")
    private String title;

    private LocalDate creation;

    private Integer qualification;

    private Long genreId;

    private Set<Characters> characters = new HashSet<>();
}
