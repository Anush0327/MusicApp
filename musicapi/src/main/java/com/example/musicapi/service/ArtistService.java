package com.example.musicapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.musicapi.DTO.ArtistDTO;
import com.example.musicapi.DTO.SongDTO;
import com.example.musicapi.entity.Artist;
import com.example.musicapi.repository.ArtistRepository;

@Service
public class ArtistService {
    
    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private SongService songService;

    public List<ArtistDTO> getAllArtists(){
        List<ArtistDTO> artists = new ArrayList<>();
        artistRepository.findAll().forEach(artist -> {
            ArtistDTO newArtistDTO = new ArtistDTO();
            newArtistDTO.setArtistName(artist.getArtistName());
            newArtistDTO.setComposer(artist.isComposer());
            artists.add(newArtistDTO);
        });
        return artists;
    }

    public List<SongDTO> getAllSongsOfArtist(String artistName){
        List<SongDTO> songs = new ArrayList<>();
        Optional<Artist> optArtist = artistRepository.findByArtistName(artistName);
        Artist newArtist = new Artist();
        if(optArtist.isPresent()){
            newArtist = optArtist.get();
        }
        else{
            return null;
        }
        if(!newArtist.isComposer()){
        songs = songService.getAllLibrarySongs().stream().
        filter(song->song.getArtists()
                            .stream().filter(artist -> artist.equals(artistName))
                            .findFirst().isPresent())
                            .collect(Collectors.toList());
        }
        else{
        songs = songService.getAllLibrarySongs().stream().
        filter(song->song.getComposers()
                            .stream().filter(artist -> artist.equals(artistName))
                            .findFirst().isPresent())
                            .collect(Collectors.toList());
        }
        return songs;
    }
}
