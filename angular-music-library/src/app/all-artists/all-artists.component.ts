import { Component } from '@angular/core';
import { Artist } from '../model/artist';
import { MusicService } from '../music.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-all-artists',
  templateUrl: './all-artists.component.html',
  styleUrls: ['./all-artists.component.css']
})
export class AllArtistsComponent {
  artists: Artist[] = [];

  constructor(private musicService: MusicService, private router: Router) {}

  ngOnInit(): void {
    this.musicService.getArtists().subscribe(res => {
      this.artists = res;
    })
  }

  getArtistSongs(artistName: string): void {
    console.log(artistName);
    this.router.navigate(["/artist", artistName]);
  }
}
