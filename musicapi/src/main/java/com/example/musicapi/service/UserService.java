package com.example.musicapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.musicapi.entity.User;
import com.example.musicapi.exception.MusicLibraryBusinessException;
import com.example.musicapi.repository.UserRepository;

@Service
public class UserService {
    
    private BCryptPasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public UserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Optional<User> authenticate(String username, String password) {
        Optional<User> optUser = userRepository.getByName(username);
        if (optUser.isEmpty()) {
            throw new MusicLibraryBusinessException("User not found");
        }
        if (!optUser.get().getPassword().equals(password)) {
            return Optional.empty();
        }
        return optUser;
    }

    public User create(User user) {
        user.setPassword("{bcrypt}" + passwordEncoder.encode(user.getPassword()));
        //user.setPassword("{noop}" + user.getPassword());
        return userRepository.save(user);
    }

    public Optional<User> getById(int id) {
        return userRepository.findById(id);
    }

    public Optional<User> getByName(String name) {
        return userRepository.getByName(name);
    }
    
    public User getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.getByName(authentication.getName());
        if(user.isPresent())
            return user.get();
        else
            return null;
    }
}
