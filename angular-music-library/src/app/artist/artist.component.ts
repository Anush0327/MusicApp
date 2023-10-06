import { Component } from '@angular/core';
import { Song } from '../model/song';
import { ActivatedRoute } from '@angular/router';
import { MusicService } from '../music.service';

@Component({
  selector: 'app-artist',
  templateUrl: './artist.component.html',
  styleUrls: ['./artist.component.css', '../all-songs/all-songs.component.css']
})
export class ArtistComponent {
  artistName: string = "";
  songs: Song[] = [];
  currentlyPlaying: string = "";

  constructor(private route: ActivatedRoute, private musicService: MusicService){}

  ngOnInit(): void {
    this.route.params.subscribe(param => {
      this.artistName = param['artistName'];
      this.musicService.getArtistSongs(this.artistName).subscribe(res => {
        this.songs = res;
      });
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
