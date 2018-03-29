package com.chessa.example.mongorest.RestMongoReadhbase.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Time;

@Document(collection="tweet")
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
//        allowGetters = true)
public class Twitter {
    @Id
    private String _id;

//    @NotBlank
    private String username;

//    @NotBlank
    private String hastag;

//    @NotBlank
    private String waktu;

//    @NotBlank
    private String text;


    public Twitter() {
        super();
    }

    public Twitter(String _id, String username, String hastag, String waktu, String text) {
        this._id = _id;
        this.username = username;
        this.hastag = hastag;
        this.waktu = waktu;
        this.text = text;
    }

    public Twitter(String username, String hastag, String waktu, String text) {
        this.username = username;
        this.hastag = hastag;
        this.waktu = waktu;
        this.text = text;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHastag() {
        return hastag;
    }

    public void setHastag(String hastag) {
        this.hastag = hastag;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Twitter{" +
                "_id=" + _id +
                ", username='" + username + '\'' +
                ", hastag='" + hastag + '\'' +
                ", waktu=" + waktu +
                ", text='" + text + '\'' +
                '}';
    }
}
