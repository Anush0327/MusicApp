package com.example.musicapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.musicapi.DTO.AlbumDTO;
import com.example.musicapi.DTO.ArtistDTO;
import com.example.musicapi.DTO.SongDTO;
import com.example.musicapi.service.AlbumService;
import com.example.musicapi.service.ArtistService;
import com.example.musicapi.service.PlayListService;
import com.example.musicapi.service.SongService;
import com.netflix.discovery.converters.Auto;

@RestController
@RequestMapping("/api/music")
public class MusicController {
    
    @Autowired
    private SongService songService;

    @Autowired
    private PlayListService playListService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private ArtistService artistService;

    @GetMapping("/{name}")
    public SongDTO getSongByName(@PathVariable String name){
        return songService.getSongByname(name);
    }

    @GetMapping("/allSongs")
    public List<SongDTO> getAllLibrarySongs(){
        return songService.getAllLibrarySongs();
    }

    @PostMapping("/library/add")
    public void addSongToLibrary(@RequestBody SongDTO songDTO){
        songService.addSongToLibrary(songDTO);
    }

    @GetMapping("/allAlbums")
    public List<AlbumDTO> getAllAlbums(){
        return albumService.getAllAlbums();
    }

    @GetMapping("/allArtists")
    public List<ArtistDTO> getAllArtists(){
        return artistService.getAllArtists();
    }

    @GetMapping("/album")
    public List<SongDTO> getAllSongsOfAlbum(@RequestParam String albumName){
        return albumService.getAllSongsInAlbum(albumName);
    }

    @GetMapping("/artist")
    public List<SongDTO> getAllSongsOfArtist(@RequestParam String artistName){
        return artistService.getAllSongsOfArtist(artistName);
    }

}