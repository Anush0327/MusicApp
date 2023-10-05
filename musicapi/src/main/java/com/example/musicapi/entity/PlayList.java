package com.example.musicapi.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class PlayList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String playListName;
    
    @ManyToOne
    @JoinColumn(name= "user_id",referencedColumnName = "id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Song> songs = new ArrayList<>();
}
