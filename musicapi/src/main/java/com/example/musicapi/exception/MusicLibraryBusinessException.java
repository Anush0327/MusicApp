package com.example.musicapi.exception;



public class MusicLibraryBusinessException extends RuntimeException{
    public MusicLibraryBusinessException(String message) {
        super(message);
    }
}