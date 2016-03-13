package edu.lewisu.cs.peterschilder.gamerating;

import java.util.UUID;

/**
 * Created by Peter Schilder on 3/6/2016.
 */
public class Rate {
    private UUID id;
    private String name;
    private String comment;
    private int category;
    private Float rating;

    public Rate() {
        id = UUID.randomUUID();
    }

    public Rate(UUID id, String name, String comment,int category, Float rating) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.category = category;
        this.rating = rating;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}