package com.glowingsoft.bushdigital;


import java.util.ArrayList;
import java.util.List;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class CategoriesModel {

    @io.objectbox.annotation.Id
    long Id;

    private String title;
    private String id_site;

    public CategoriesModel() {
    }

    public CategoriesModel(long id, String title, String id_site ) {
        Id = id;
        this.title = title;
        this.id_site = id_site;

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

    public String getId_site() {
        return id_site;
    }

    public void setId_site(String id_site) {
        this.id_site = id_site;
    }
}
