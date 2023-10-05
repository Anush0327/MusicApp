import { Component } from '@angular/core';
import { Song } from '../model/song';
import { MusicService } from '../music.service';

@Component({
  selector: 'app-all-songs',
  templateUrl: './all-songs.component.html',
  styleUrls: ['./all-songs.component.css']
})
export class AllSongsComponent {
  songs: Song[] = [];
  currentlyPlaying: string = "";


  constructor(private musicService: MusicService) {}

  ngOnInit(): void {
    this.musicService.getAllSongs().subscribe(res => {
      this.songs = res;
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
}
