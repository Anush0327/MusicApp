package com.example.musicapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.musicapi.entity.Song;
import com.example.musicapi.entity.User;
import com.example.musicapi.entity.UserLibrary;

public interface UserLibraryRepository extends JpaRepository<UserLibrary,Integer>{

    UserLibrary findByUser(User user);
    
}
