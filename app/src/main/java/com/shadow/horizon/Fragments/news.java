package com.shadow.horizon.Fragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class news extends AppCompatActivity {
String author;
    String titl;
    String des;
    String publish;
    String url;
    String urlImage;

    public news() {
    }

    public news(String author, String titl, String des, String publish, String url, String urlImage) {
        this.author = author;
        this.titl = titl;
        this.des = des;
        this.publish = publish;
        this.url = url;
        this.urlImage = urlImage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getTitl() {
        return titl;
    }

    public void setTitl(String titl) {
        this.titl = titl;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
