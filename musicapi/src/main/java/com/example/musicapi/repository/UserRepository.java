package com.example.musicapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.musicapi.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{
    
}
