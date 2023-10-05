package com.example.musicapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.musicapi.DTO.SongDTO;
import com.example.musicapi.entity.Artist;
import com.example.musicapi.entity.PlayList;
import com.example.musicapi.entity.Song;
import com.example.musicapi.entity.User;
import com.example.musicapi.entity.UserLibrary;
import com.example.musicapi.repository.PlayListRepository;
import com.example.musicapi.repository.UserRepository;
import com.nimbusds.jose.proc.SecurityContext;

@Service
public class PlayListService {
    
    @Autowired
    private PlayListRepository playListRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SongService songService;

    public boolean allreadyExistingSong(PlayList playList,SongDTO songDTO){
        return playList.getSongs().stream().anyMatch(song -> songDTO.getTitle().equals(song.getTitle()));
    }
    
    public void addToPlayList(SongDTO songDTO){
        User user = userService.getLoggedInUser();
        PlayList playList = playListRepository.findPlayListByUser(user);
        if(playList == null)
        {
            playList = new PlayList();
            playList.setUser(user);
        }
        if(!allreadyExistingSong(playList, songDTO)){
            Song song = songService.convertDTOToSong(songDTO);
            playList.getSongs().add(song);
            playListRepository.save(playList);
        }
    }

    public List<SongDTO> getAllPlayListSongs(){
        List<SongDTO> songs = new ArrayList<>();
        User user = userService.getLoggedInUser();
        PlayList playList = playListRepository.findPlayListByUser(user);;
        playList.getSongs().stream().forEach(song -> songs.add(songService.convertSongToDTO(song)));
        return songs;
    }
}
