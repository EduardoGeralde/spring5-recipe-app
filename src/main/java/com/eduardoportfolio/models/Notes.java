package com.eduardoportfolio.models;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Eduardo on 09/11/17.
 */
@Data
@EqualsAndHashCode (exclude = "recipe")
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Recipe recipe;
    @Lob
    private String recipeNotes;

}
