package com.example.musicapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.musicapi.entity.Artist;

public interface ArtistRepository extends JpaRepository<Artist,Integer> {
    
}
