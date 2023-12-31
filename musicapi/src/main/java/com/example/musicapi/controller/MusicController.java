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
import com.example.musicapi.DTO.PlayListDTO;
import com.example.musicapi.DTO.SongDTO;
import com.example.musicapi.service.AlbumService;
import com.example.musicapi.service.ArtistService;
import com.example.musicapi.service.PlayListService;
import com.example.musicapi.service.SongService;

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
        return playListService.getAllSongsAfterLiked(songService.getAllLibrarySongs(), playListService.getLikedSongs());
    }

    @PostMapping("/library/add")
    public void addSongToLibrary(@RequestBody SongDTO songDTO){
        songService.addSongToLibrary(songDTO);
    }

    @PostMapping("/playlist/add")
    public void addSongTOPlayList(@RequestParam String playlistName,@RequestBody String title){
        System.out.println(title);
        playListService.addToPlayList(songService.getSongByname(title), playlistName);
    }

    @GetMapping("/allPlaylists")
    public List<PlayListDTO> getAllPlayLists(){
        return playListService.getAllPlayLists();
    }

    @GetMapping("/playlist")
    public List<SongDTO> getAllPlayListSongs(@RequestParam String playListName){
        return playListService.getAllSongsAfterLiked(playListService.getAllPlayListSongs(playListName), playListService.getLikedSongs());
    }

    @PostMapping("/addToLiked")
    public void addSongToLiked(@RequestBody String title){
        System.out.println(title);
        playListService.addToLikedSongs(songService.getSongByname(title));
    }

    @GetMapping("/likedSongs")
    public List<SongDTO> getLikedSongs(){
        return playListService.getLikedSongs();
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
        return playListService.getAllSongsAfterLiked(albumService.getAllSongsInAlbum(albumName), playListService.getLikedSongs());
    }

    @GetMapping("/artist")
    public List<SongDTO> getAllSongsOfArtist(@RequestParam String artistName){
        return playListService.getAllSongsAfterLiked(artistService.getAllSongsOfArtist(artistName), playListService.getLikedSongs());
    }

}