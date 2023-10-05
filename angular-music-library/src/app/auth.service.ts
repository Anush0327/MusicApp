import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }
  authUrl = "http://localhost:8080/api/auth";

  login(username: any, password: any): Observable<any> {
    return this.http.post<any>(`${this.authUrl}/auth/token`, { username: username, password: password }).pipe(
      tap(res => {
        localStorage.setItem('token', res["token"]);
        localStorage.setItem('username', res["username"]);
      })
    );
  }
}
