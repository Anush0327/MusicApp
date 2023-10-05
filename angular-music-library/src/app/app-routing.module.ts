import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LibraryComponent } from './library/library.component';
import { AllAlbumsComponent } from './all-albums/all-albums.component';

const routes: Routes = [
  {path: "", component: HomeComponent},
  {path: "library", component: LibraryComponent},
  {path: "albums", component: AllAlbumsComponent},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
