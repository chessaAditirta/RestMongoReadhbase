package com.chessa.example.mongorest.RestMongoReadhbase.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Document(collection="tweet")
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
//        allowGetters = true)
public class Twitter {
    @Id
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String hastag;

    @NotBlank
    private String waktu;

    @NotBlank
    private String text;

    private Boolean completed = false;

    public Twitter() {
        super();
    }

    public Twitter(@NotBlank String username, @NotBlank String hastag, @NotBlank String waktu, @NotBlank String text) {
        this.username = username;
        this.hastag = hastag;
        this.waktu = waktu;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Twitter{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", hastag='" + hastag + '\'' +
                ", waktu='" + waktu + '\'' +
                ", text='" + text + '\'' +
                ", completed=" + completed +
                '}';
    }
}
