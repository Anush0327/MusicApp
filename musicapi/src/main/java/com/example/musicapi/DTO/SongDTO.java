package com.example.musicapi.DTO;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SongDTO {
    
    private String title;

    private List<String> artists = new ArrayList<>();

    private List<String> composers = new ArrayList<>();

    private String albumName;

    private int year;
}
