package com.example.musicapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.musicapi.DTO.AlbumDTO;
import com.example.musicapi.DTO.SongDTO;
import com.example.musicapi.repository.AlbumRepositry;

@Service
public class AlbumService {

    @Autowired
    private SongService songService;

    @Autowired
    private AlbumRepositry albumRepositry;

    public List<SongDTO> getAllSongsInAlbum(String albumName){
        List<SongDTO> albumSongs = new ArrayList<>();
        albumSongs = songService.getAllLibrarySongs().stream().
                                        filter(song -> song.getAlbumName().equals(albumName)).
                                        collect(Collectors.toList());
        return albumSongs;
    }

    public List<AlbumDTO> getAllAlbums(){
        List<AlbumDTO> albums = new ArrayList<>();
        albumRepositry.findAll().forEach(album -> {
            AlbumDTO newAlbumDTO = new AlbumDTO();
            newAlbumDTO.setSongs(getAllSongsInAlbum(album.getAlbumName()));
            newAlbumDTO.setNumberOfSongs(newAlbumDTO.getSongs().size());
            albums.add(newAlbumDTO);
        });
        return albums;
    }

}
