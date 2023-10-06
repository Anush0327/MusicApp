import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Song } from '../model/song';
import { MusicService } from '../music.service';

@Component({
  selector: 'app-album',
  templateUrl: './album.component.html',
  styleUrls: ['./album.component.css', '../all-songs/all-songs.component.css']
})
export class AlbumComponent {
  albumName: string = "";
  songs: Song[] = [];
  currentlyPlaying: string = "";

  constructor(private route: ActivatedRoute, private musicService: MusicService){}

  ngOnInit(): void {
    this.route.params.subscribe(param => {
      this.albumName = param['albumName'];
      this.musicService.getAlbumSongs(this.albumName).subscribe(res => {
        this.songs = res;
      })
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
}
