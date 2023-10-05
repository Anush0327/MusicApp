package com.example.musicapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.musicapi.entity.PlayList;
import com.example.musicapi.entity.User;

public interface PlayListRepository extends JpaRepository<PlayList,Integer>{
    
    PlayList findPlayListByUser(User user);

    PlayList findPlayListByUserAndPlayListName(User user, String playListName);

}
