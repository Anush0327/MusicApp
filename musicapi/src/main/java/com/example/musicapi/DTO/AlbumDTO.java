package com.example.musicapi.DTO;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class AlbumDTO {
    
    private String albumName;

    private int numberOfSongs;

    private int year;
}
