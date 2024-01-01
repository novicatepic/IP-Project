import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseUrl = environment.loginUrl;

  private usernameUrl = environment.usernameUrl;

  constructor(private http:HttpClient) { }

  loginFitnessUser(fitnessUser: object): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.baseUrl}`, JSON.stringify(fitnessUser), {headers});
  }

  getByUsername(username: any): Observable<any> {
    const url = this.usernameUrl + username;
    return this.http.get(`${url}`);
  }
}
