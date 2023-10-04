package com.example.musicapi.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String albumName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Song> songs = new ArrayList<>();
}
