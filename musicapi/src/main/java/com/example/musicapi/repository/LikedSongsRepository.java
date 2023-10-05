package com.example.musicapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.musicapi.entity.LikedSongs;
import com.example.musicapi.entity.User;

public interface LikedSongsRepository extends JpaRepository<LikedSongs,Integer>{
    public LikedSongs findLikedSongsByUser(User user);
}
