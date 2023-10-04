package com.example.musicapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.musicapi.entity.UserLibrary;

public interface UserLibraryRepository extends JpaRepository<UserLibrary,Integer>{
    
}
