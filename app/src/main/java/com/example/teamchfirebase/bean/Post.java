package com.example.teamchfirebase.bean;

import android.net.Uri;

public class Post {

    private String Titre;
    private String Description;
    private String Image ;

    public Post(String titre, String description, String image) {
        Titre = titre;
        Description = description;
        Image = image;
    }

    public Post(){}


    public String getTitre() {
        return Titre;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }





}
