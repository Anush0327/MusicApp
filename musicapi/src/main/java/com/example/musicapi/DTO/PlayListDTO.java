package com.example.musicapi.DTO;

import java.util.List;

import lombok.Data;

@Data
public class PlayListDTO {
    private String playListName;

    private List<SongDTO> songs;
}
