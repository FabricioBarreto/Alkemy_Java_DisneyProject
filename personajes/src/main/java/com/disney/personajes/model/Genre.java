package com.disney.personajes.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "genre")
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;

}
