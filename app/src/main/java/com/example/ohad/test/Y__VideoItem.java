package com.example.ohad.test;

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//class of the structure of results in the search layout
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

public class Y__VideoItem {
    private String description;
    private String id;
    private String thumbnailURL;
    private String title;



    Y__VideoItem(){ }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnail) {
        this.thumbnailURL = thumbnail;
    }

}