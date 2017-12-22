package com.eduardoportfolio.models;

import lombok.Data;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Eduardo on 09/11/17
 */
@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    @Lob
    private String directions;
    @Lob
    private Byte[] image;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @OneToOne (cascade = CascadeType.ALL)
    private Notes notes;

    @Enumerated(value=EnumType.STRING)
    private Difficulty difficulty;

    @ManyToMany
    @JoinTable(name="recipe_category", joinColumns = @JoinColumn(name="recipe_id"),
            inverseJoinColumns = @JoinColumn (name="category_id") )
    private Set<Category> categories = new HashSet<>();


    public void setNotes(Notes notes) {
        if(notes != null) {
            this.notes = notes;
            notes.setRecipe(this);
        }
    }

    public Recipe addIngredient (Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
}
