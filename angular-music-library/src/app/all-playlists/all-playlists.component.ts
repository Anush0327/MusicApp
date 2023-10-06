import { Component } from '@angular/core';
import { Playlist } from '../model/playlist';
import { MusicService } from '../music.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-all-playlists',
  templateUrl: './all-playlists.component.html',
  styleUrls: ['./all-playlists.component.css', '../all-albums/all-albums.component.css']
})
export class AllPlaylistsComponent {
  playlists: Playlist[] = [{playListName: "Liked Songs", numberOfSongs: 0}];

  constructor(private musicService: MusicService, private router: Router) {}

  ngOnInit(): void {
    this.musicService.getPlaylists().subscribe(res => {
      res.forEach(playlist => this.playlists.push(playlist));
      this.musicService.getLikedSongs().subscribe(res => {
        this.playlists[0].numberOfSongs = res.length;
      });
    });
  }

  getAlbumSongs(playlistName: string): void {
    this.router.navigate(["/playlist", playlistName]);
  }
}
