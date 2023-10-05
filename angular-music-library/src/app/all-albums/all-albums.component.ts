import { Component } from '@angular/core';
import { Album } from '../model/album';
import { MusicService } from '../music.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-all-albums',
  templateUrl: './all-albums.component.html',
  styleUrls: ['./all-albums.component.css']
})
export class AllAlbumsComponent {
  albums: Album[] = [];

  constructor(private musicService: MusicService, private router: Router) {}

  ngOnInit(): void {
    this.musicService.getAlbums().subscribe(res => {
      this.albums = res;
    });
  }

  getAlbumSongs(albumName: string): void {
    this.router.navigate(["/album", albumName]);
  }
}
