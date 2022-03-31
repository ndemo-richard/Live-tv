package com.KLTC.model;
import com.google.gson.annotations.SerializedName;



public class Radio {

    @SerializedName("id") private int id;
    @SerializedName("title") private String title;
    @SerializedName("description") private String description;
    @SerializedName("localImageResource") private String localImageResource;
    @SerializedName("url") private String url;
    @SerializedName("imageUrl") private String imageUrl;

    public Radio(){}

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public  String getImageUrl(){return imageUrl;}

    public void setImageUrl(String imageUrl){this.imageUrl=imageUrl;}

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url=url;
    }
}

