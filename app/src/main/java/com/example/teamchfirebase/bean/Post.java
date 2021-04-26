package com.example.teamchfirebase.bean;

import android.net.Uri;

public class Post {

    private String Titre;
    private String Description;
    private String Image ;
    private String comment;

    public Post(String titre, String description, String image ,String Comment) {
        Titre = titre;
        Description = description;
        Image = image;
        comment=Comment;
    }

    public Post(){}

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
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
