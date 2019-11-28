package com.glowingsoft.bushdigital;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.annotation.Entity;

@Entity
public class CategoryModelMainList {
    @io.objectbox.annotation.Id
    long Id;


    private List<CategoryModelLocal> categoryModelLocals = new ArrayList<>();

    public CategoryModelMainList() {
    }

    public CategoryModelMainList(long id,List<CategoryModelLocal> categoryModelLocals) {
        Id = id;
        this.categoryModelLocals = categoryModelLocals;

    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public List<CategoryModelLocal> getCategoryModelLocals() {
        return categoryModelLocals;
    }

    public void setCategoryModelLocals(List<CategoryModelLocal> categoryModelLocals) {
        this.categoryModelLocals = categoryModelLocals;
    }
}
