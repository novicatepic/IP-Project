import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseUrl = 'http://localhost:4040/auth/login';

  private usernameUrl = 'http://localhost:4040/fitness-users/user/';

  constructor(private http:HttpClient) { }

  loginFitnessUser(fitnessUser: object): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.baseUrl}`, JSON.stringify(fitnessUser), {headers});
  }

  getByUsername(username: any): Observable<any> {
    this.usernameUrl += username;
    return this.http.get(`${this.usernameUrl}`);
  }
}
