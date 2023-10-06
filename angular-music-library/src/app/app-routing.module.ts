import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LibraryComponent } from './library/library.component';
import { AllAlbumsComponent } from './all-albums/all-albums.component';
import { AlbumComponent } from './album/album.component';
import { AllSongsComponent } from './all-songs/all-songs.component';
import { LoginComponent } from './login/login.component';
import { AllArtistsComponent } from './all-artists/all-artists.component';
import { ArtistComponent } from './artist/artist.component';
import { AllPlaylistsComponent } from './all-playlists/all-playlists.component';

const routes: Routes = [
  {path: "", component: HomeComponent},
  {path: "library", component: LibraryComponent},
  {path: "songs", component: AllSongsComponent},
  {path: "albums", component: AllAlbumsComponent},
  {path: "artists", component: AllArtistsComponent},
  {path: "playlists", component: AllPlaylistsComponent},
  {path: "login", component: LoginComponent},
  {path: "album/:albumName", component: AlbumComponent},
  {path: "artist/:artistName", component: ArtistComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
