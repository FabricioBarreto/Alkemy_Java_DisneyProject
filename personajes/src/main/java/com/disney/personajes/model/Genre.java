package com.disney.personajes.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "genre")
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Movies> movies = new ArrayList<>();

}
