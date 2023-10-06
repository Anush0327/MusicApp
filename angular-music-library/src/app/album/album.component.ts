import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Song } from '../model/song';
import { MusicService } from '../music.service';
import { Playlist } from '../model/playlist';

@Component({
  selector: 'app-album',
  templateUrl: './album.component.html',
  styleUrls: ['./album.component.css', '../all-songs/all-songs.component.css']
})
export class AlbumComponent {
  albumName: string = "";
  songs: Song[] = [];
  currentlyPlaying: string = "";
  playlistHidden: boolean = true;
  selectedSong: string = "";
  playlists: Playlist[] = [];

  constructor(private route: ActivatedRoute, private musicService: MusicService){}

  ngOnInit(): void {
    this.route.params.subscribe(param => {
      this.albumName = param['albumName'];
      this.musicService.getAlbumSongs(this.albumName).subscribe(res => {
        this.songs = res;
      })
    });
    this.musicService.getPlaylists().subscribe(res => {
      this.playlists = res;
      console.log(res);
    });
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
    this.musicService.addToPlaylist(playlistName, this.selectedSong).subscribe(res => {
      this.ngOnInit();
    });
    this.selectedSong = "";
  }
}
