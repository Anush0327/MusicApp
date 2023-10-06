import { Component } from '@angular/core';
import { MusicService } from '../music.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  title : string= "";
  artists: string = "";
  composers: string = "";
  albumName: string = "";
  year: number = 0;

  convertToList(param : string): string[] {
    var res = param.split(',');
    return res;
  }

  constructor(private musicService : MusicService, private router: Router){}

  submitForm() {
    let song = {
      title: this.title,
      artists: this.convertToList(this.artists),
      composers: this.convertToList(this.composers),
      albumName: this.albumName,
      year: 0
    };
    this.musicService.addSong(song).subscribe(
      (response) => {
        console.log('Song added successfully:', response);
        this.router.navigate(['songs']);
      }
    );

  }

}

 