import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent {

  constructor(private router: Router, public authService: AuthService) {}

  ngOnInit(): void {}

  toggleLogin(){
    if(this.authService.isAuthenticated()){
      localStorage.removeItem("token");
      localStorage.removeItem("username");
    }
    this.router.navigate(['login']);
  }
}
