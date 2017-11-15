package com.eduardoportfolio.models;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Eduardo on 09/11/17.
 */
@Data
@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

}
