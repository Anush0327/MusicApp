package com.example.musicapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.musicapi.entity.Album;

public interface AlbumRepositry extends JpaRepository<Album,Integer>{

    Optional<Album> findByAlbumName(String albumName);
    
}
