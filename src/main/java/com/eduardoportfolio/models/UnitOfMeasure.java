package com.eduardoportfolio.models;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Eduardo on 09/11/17
 */
@Data
/*
 * If we're not using lombok, we make default constructor for JPA.
 * It's needed when we make this object in controller. If lombok is in use,
 * good practice is tu create default constructor with annotation
 */
@NoArgsConstructor
/*
 * In your data.sql You use table: unit_of_measure, but JPA will create table in according with Entity name.
 * In your case by default it will unitofmeasure or unitOfMeasure, but not unit_of_measure
 */
@Entity(name = "unit_of_measure")
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

}
