import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Song } from './model/song';
import { Album } from './model/album';
import { Artist } from './model/artist';
import { Playlist } from './model/playlist';

@Injectable({
  providedIn: 'root'
})
export class MusicService {

  constructor(private http: HttpClient) { }
  musicUrl = "http://localhost:8080/api/music";

  getHeader(): HttpHeaders {
    if (localStorage.getItem('token')) {
      const headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('token')
      });
      return headers;
    }
    return new HttpHeaders({});
  }

  getAllSongs(): Observable<Song[]> {
    return this.http.get<Song[]>(`${this.musicUrl}/allSongs`, {headers: this.getHeader()});
  }

  getAlbums(): Observable<Album[]> {
    return this.http.get<Album[]>(`${this.musicUrl}/allAlbums`, {headers: this.getHeader()});
  }

  getArtists(): Observable<Artist[]> {
    return this.http.get<Artist[]>(`${this.musicUrl}/allArtists`, {headers: this.getHeader()});
  }

  getPlaylists(): Observable<Playlist[]> {
    return this.http.get<Playlist[]>(`${this.musicUrl}/allPlaylists`, {headers: this.getHeader()});
  }

  getAlbumSongs(albumName: string): Observable<Song[]> {
    return this.http.get<Song[]>(`${this.musicUrl}/album?albumName=${albumName}`, {headers: this.getHeader()});
  }

  getArtistSongs(artistName: string): Observable<Song[]> {
    return this.http.get<Song[]>(`${this.musicUrl}/artist?artistName=${artistName}`, {headers: this.getHeader()});
  }

  getPlaylistSongs(playlistName: string): Observable<Song[]> {
    if(playlistName == "Liked Songs")
      return this.http.get<Song[]>(`${this.musicUrl}/likedSongs`, {headers: this.getHeader()});
    else
      return this.http.get<Song[]>(`${this.musicUrl}/playlist?playListName=${playlistName}`, {headers: this.getHeader()});
  }

  getLikedSongs(): Observable<Song[]> {
    return this.http.get<Song[]>(`${this.musicUrl}/likedSongs`, {headers: this.getHeader()});
  }

  addSong(song: any): Observable<any> {
    return this.http.post(`${this.musicUrl}/library/add`, song, {headers: this.getHeader()});
  }

  toggleLike(title: string): Observable<void> {
    return this.http.post<void>(`${this.musicUrl}/addToLiked`, title, {headers: this.getHeader()});
  }

  addToPlaylist(playlistName: string, title: string) {
    return this.http.post<void>(`${this.musicUrl}/playlist/add?playlistName=${playlistName}`, title, {headers: this.getHeader()});
  }

}