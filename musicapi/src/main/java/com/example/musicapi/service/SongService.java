package com.example.musicapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.musicapi.DTO.SongDTO;
import com.example.musicapi.entity.Album;
import com.example.musicapi.entity.Artist;
import com.example.musicapi.entity.Song;
import com.example.musicapi.entity.User;
import com.example.musicapi.entity.UserLibrary;
import com.example.musicapi.repository.AlbumRepositry;
import com.example.musicapi.repository.ArtistRepository;
import com.example.musicapi.repository.SongRepository;
import com.example.musicapi.repository.UserLibraryRepository;


@Service
public class SongService {
    
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private UserLibraryRepository userLibraryRepository;

    @Autowired
    private AlbumRepositry albumRepositry;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private UserService userService;

    public SongDTO getSongByname(String name){
        Song song = songRepository.findByTitle(name).orElseGet(null);
        SongDTO songDTO = new SongDTO();
        if(song!=null){
            songDTO.setTitle(song.getTitle());
            song.getArtists().stream().map(artist -> songDTO.getArtists().add(artist.getArtistName())).collect(Collectors.toList());
        }
        return songDTO;
    }

    public Song convertDTOToSong(SongDTO songDTO){
        Song song = new Song();
        song.setTitle(songDTO.getTitle());
        songDTO.getArtists().stream().forEach(artist ->{
            Optional<Artist> newArtist = artistRepository.findByArtistName(artist);
            if(newArtist.isPresent()){
                song.getArtists().add(newArtist.get());
            }
            else{
                Artist newArtist2 = new Artist();
                newArtist2.setArtistName(artist);
                artistRepository.save(newArtist2);
                song.getArtists().add(newArtist2);
            }
        });
        songDTO.getComposers().stream().forEach(artist ->{
            Optional<Artist> newArtist = artistRepository.findByArtistName(artist);
            if(newArtist.isPresent()){
                song.getArtists().add(newArtist.get());
            }
            else{
                Artist newArtist2 = new Artist();
                newArtist2.setArtistName(artist);
                newArtist2.setComposer(true);
                artistRepository.save(newArtist2);
                song.getArtists().add(newArtist2);
            }
        });
        Optional<Album> album = albumRepositry.findByAlbumName(songDTO.getAlbumName());
        if(album.isPresent()){
            song.setAlbum(album.get());
        }
        else{
            Album newAlbum = new Album();
            newAlbum.setAlbumName(songDTO.getAlbumName());
            newAlbum.setYear(songDTO.getYear());
            albumRepositry.save(newAlbum);
            song.setAlbum(newAlbum);
        }
        return song;
    }
    public SongDTO convertSongToDTO(Song song){
        SongDTO songDTO = new SongDTO();
        songDTO.setTitle(song.getTitle());
        song.getArtists().stream().forEach(artist -> songDTO.getArtists().add(artist.getArtistName()));
        songDTO.setAlbumName(song.getAlbum().getAlbumName());
        songDTO.setYear(song.getAlbum().getYear());
        return songDTO;
    }

    public List<SongDTO> getAllLibrarySongs(){
        List<SongDTO> songs = new ArrayList<>();
        User user = userService.getLoggedInUser();
        UserLibrary userLibrary = userLibraryRepository.findByUser(user); 
        userLibrary.getSongs().stream().forEach(song -> songs.add(convertSongToDTO(song)));
        return songs;
    }

    public boolean songAllReadyInLibrary(SongDTO songDTO,UserLibrary userLibrary){
        return userLibrary.getSongs().stream()
                                    .filter(song -> songDTO.getTitle().equals(song.getTitle()))
                                    .findFirst().isPresent(); 
    }

    public void addSongToLibrary(SongDTO songDTO){
        User user = userService.getLoggedInUser();
        UserLibrary userLibrary = userLibraryRepository.findByUser(user);
        if(userLibrary == null)
        {
            userLibrary = new UserLibrary();
            userLibrary.setUser(user);
        }
        if(userLibrary.getSongs()==null){
            userLibrary.setSongs(new ArrayList<>());
        }
        if(!songAllReadyInLibrary(songDTO, userLibrary))
        {
            Song song = convertDTOToSong(songDTO);
            userLibrary.getSongs().add(song);
            songRepository.save(song);
            userLibraryRepository.save(userLibrary);
        }
    }
}
