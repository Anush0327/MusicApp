package com.example.musicapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.musicapi.entity.Song;

public interface SongRepository extends JpaRepository<Song,Integer>{

    Optional<Song> findByTitle(String name);
    
}
