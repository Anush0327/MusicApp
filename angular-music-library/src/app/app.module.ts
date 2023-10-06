import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { HomeComponent } from './home/home.component';
import { LibraryComponent } from './library/library.component';
import { HttpClientModule } from '@angular/common/http';
import { AllAlbumsComponent } from './all-albums/all-albums.component';
import { AlbumComponent } from './album/album.component';
import { AllSongsComponent } from './all-songs/all-songs.component';
import { LoginComponent } from './login/login.component';
import { AllArtistsComponent } from './all-artists/all-artists.component';
import { ArtistComponent } from './artist/artist.component';
import { AllPlaylistsComponent } from './all-playlists/all-playlists.component';
import { PlaylistComponent } from './playlist/playlist.component';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    HomeComponent,
    LibraryComponent,
    AllAlbumsComponent,
    AlbumComponent,
    AllSongsComponent,
    LoginComponent,
    AllArtistsComponent,
    ArtistComponent,
    AllPlaylistsComponent,
    PlaylistComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
