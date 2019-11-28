package com.glowingsoft.bushdigital;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.objectbox.annotation.Entity;

@Entity
public class CategoryModelLocal {
    @io.objectbox.annotation.Id
    long Id;

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("p_one")
    @Expose
    private String pOne;
    @SerializedName("p_two")
    @Expose
    private String pTwo;
    @SerializedName("p_three")
    @Expose
    private String pThree;

    public CategoryModelLocal() {
    }

    public CategoryModelLocal(long id, String title, String category, String description, String image, String pOne, String pTwo, String pThree) {
        Id = id;
        this.title = title;
        this.category = category;
        this.description = description;
        this.image = image;
        this.pOne = pOne;
        this.pTwo = pTwo;
        this.pThree = pThree;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPOne() {
        return pOne;
    }

    public void setPOne(String pOne) {
        this.pOne = pOne;
    }

    public String getPTwo() {
        return pTwo;
    }

    public void setPTwo(String pTwo) {
        this.pTwo = pTwo;
    }

    public String getPThree() {
        return pThree;
    }

    public void setPThree(String pThree) {
        this.pThree = pThree;
    }

}
