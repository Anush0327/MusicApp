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
}
