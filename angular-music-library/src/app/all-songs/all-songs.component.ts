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

  constructor(private musicService: MusicService) {}

  ngOnInit(): void {
    this.musicService.getAllSongs().subscribe(res => {
      this.songs = res;
    })
  }
}
