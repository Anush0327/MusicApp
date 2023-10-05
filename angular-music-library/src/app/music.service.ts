import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Song } from './model/song';
import { Album } from './model/album';
import { Artist } from './model/artist';

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

  getAlbumSongs(albumName: string): Observable<Song[]> {
    return this.http.get<Song[]>(`${this.musicUrl}/album?albumName=${albumName}`, {headers: this.getHeader()});
  }

  getArtistSongs(artistName: string): Observable<Song[]> {
    return this.http.get<Song[]>(`${this.musicUrl}/artist?name=${artistName}`, {headers: this.getHeader()});
  }

}
