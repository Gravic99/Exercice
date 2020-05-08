package com.example.cours9;

import java.util.Date;

public class ToDo {
    Date dateAdded;
    String title;
    String Description;

    public ToDo(Date dateAdded, String title, String description) {
        this.dateAdded = dateAdded;
        this.title = title;
        Description = description;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }




}
