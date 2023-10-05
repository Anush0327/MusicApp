package com.example.musicapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.musicapi.DTO.PlayListDTO;
import com.example.musicapi.DTO.SongDTO;
import com.example.musicapi.entity.LikedSongs;
import com.example.musicapi.entity.PlayList;
import com.example.musicapi.entity.Song;
import com.example.musicapi.entity.User;
import com.example.musicapi.repository.LikedSongsRepository;
import com.example.musicapi.repository.PlayListRepository;


@Service
public class PlayListService {
    
    @Autowired
    private PlayListRepository playListRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LikedSongsRepository likedSongsRepository;

    @Autowired
    private SongService songService;

    public boolean alreadyExistingInPlayList(PlayList playList,SongDTO songDTO){
        return playList.getSongs().stream().anyMatch(song -> songDTO.getTitle().equals(song.getTitle()));
    }
    
    public void addToPlayList(SongDTO songDTO,String playListName){
        User user = userService.getLoggedInUser();
        PlayList playList = playListRepository.findPlayListByUserAndPlayListName(user,playListName);
        if(playList == null)
        {
            playList = new PlayList();
            playList.setUser(user);
            playList.setPlayListName(playListName);
        }
        if(!alreadyExistingInPlayList(playList, songDTO)){
            Song song = songService.convertDTOToSong(songDTO);
            playList.getSongs().add(song);
            playListRepository.save(playList);
        }
    }

    public List<SongDTO> getAllPlayListSongs(String playListName){
        List<SongDTO> songs = new ArrayList<>();
        User user = userService.getLoggedInUser();
        PlayList playList = playListRepository.findPlayListByUserAndPlayListName(user,playListName);
        playList.getSongs().stream().forEach(song -> songs.add(songService.convertSongToDTO(song)));
        return songs;
    }

    public boolean alreadyExistingInLikedSongs(LikedSongs likedSongs,SongDTO songDTO){
        return likedSongs.getSongs().stream().anyMatch(song -> songDTO.getTitle().equals(song.getTitle()));
    }

    public List<SongDTO> changeIsLiked(List<SongDTO> songDTOs){
        User user = userService.getLoggedInUser();
        LikedSongs likedSongs = likedSongsRepository.findLikedSongsByUser(user);
        return songDTOs.stream().peek(songDTO -> {
            if(alreadyExistingInLikedSongs(likedSongs, songDTO)){
                songDTO.setLiked(true);
            }
            else{
                songDTO.setLiked(false);
            }
        }).collect(Collectors.toList());
    }

    public void addToLikedSongs(SongDTO songDTO){
        User user = userService.getLoggedInUser();
        LikedSongs likedSongs = likedSongsRepository.findLikedSongsByUser(user);
        if(likedSongs==null){
            likedSongs = new LikedSongs();
            likedSongs.setUser(user);
        }
        if(!alreadyExistingInLikedSongs(likedSongs, songDTO)){
            Song song = songService.convertDTOToSong(songDTO);
            likedSongs.getSongs().add(song);
        }
        else{
            Song song = songService.convertDTOToSong(songDTO);
            likedSongs.getSongs().remove(song);
        }
        likedSongsRepository.save(likedSongs);
    }

    public List<SongDTO> getLikedSongs(){
        List<SongDTO> songs = new ArrayList<>();
        User user = userService.getLoggedInUser();
        LikedSongs likedSongs = likedSongsRepository.findLikedSongsByUser(user);
        likedSongs.getSongs().stream().forEach(song -> songs.add(songService.convertSongToDTO(song)));
        return songs;

    }

    public List<PlayListDTO> getAllPlayLists(){
        List<PlayListDTO> playListDTOs = new ArrayList<>();
        User user = userService.getLoggedInUser();
        List<PlayList> playLists = playListRepository.findAllPlayListByUser(user);
        playLists.stream().forEach(playList -> {
            PlayListDTO playListDTO = new PlayListDTO();
            playListDTO.setPlayListName(playList.getPlayListName());
            playListDTO.setSongs(getAllPlayListSongs(playList.getPlayListName()));
            playListDTOs.add(playListDTO);
        });
        return playListDTOs;
    }
}
