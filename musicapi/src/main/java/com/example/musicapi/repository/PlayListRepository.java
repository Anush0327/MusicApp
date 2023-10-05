package com.example.musicapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.musicapi.entity.PlayList;
import com.example.musicapi.entity.User;

public interface PlayListRepository extends JpaRepository<PlayList,Integer>{
    
    List<PlayList> findAllPlayListByUser(User user);

    PlayList findPlayListByUserAndPlayListName(User user, String playListName);

}
