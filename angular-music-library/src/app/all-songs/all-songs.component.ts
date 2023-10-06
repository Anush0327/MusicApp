import { Component } from '@angular/core';
import { Song } from '../model/song';
import { MusicService } from '../music.service';
import { Playlist } from '../model/playlist';

@Component({
  selector: 'app-all-songs',
  templateUrl: './all-songs.component.html',
  styleUrls: ['./all-songs.component.css']
})
export class AllSongsComponent {
  songs: Song[] = [];
  currentlyPlaying: string = "";
  playlistHidden: boolean = true;
  selectedSong: string = "";
  playlists: Playlist[] = [];


  constructor(private musicService: MusicService) {}

  ngOnInit(): void {
    this.musicService.getAllSongs().subscribe(res => {
      this.songs = res;
      console.log(res);
    });
    this.musicService.getPlaylists().subscribe(res => {
      this.playlists = res;
      console.log(res);
    })
  }

  togglePlay(title: string) {
    if(this.currentlyPlaying == title) {
      this.currentlyPlaying = "";
    }
    else {
      this.currentlyPlaying = title;
    }
  }

  toggleLike(title: string) {
    this.musicService.toggleLike(title).subscribe(res => {
      this.ngOnInit();
    })
  }

  togglePlaylist(title: string): void {
    this.playlistHidden = !this.playlistHidden;
    if(!this.playlistHidden) {
      this.selectedSong = title;
    }
    else {
      this.selectedSong = "";
    }
  }

  addToPlaylist(playlistName: string) {
    console.log(this.selectedSong + "100");
    this.musicService.addToPlaylist(playlistName, this.selectedSong).subscribe(res => {
      this.ngOnInit();
    });
    this.selectedSong = "";
  }
}
