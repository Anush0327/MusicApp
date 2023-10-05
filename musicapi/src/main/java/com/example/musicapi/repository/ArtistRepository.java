package com.example.musicapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.musicapi.entity.Artist;
import com.example.musicapi.entity.Song;

public interface ArtistRepository extends JpaRepository<Artist,Integer> {

    Optional<Artist> findByArtistName(String artist);
    
}
