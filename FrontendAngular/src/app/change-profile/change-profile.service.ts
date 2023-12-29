import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import * as jwtDecode from 'jwt-decode';
import { JwtTokenService } from '../jwt-token/jwt-token.service';

@Injectable({
  providedIn: 'root'
})
export class ChangeProfileService {

  private baseUrl = 'http://localhost:4040/fitness-users';

  private updatePWUrl = 'http://localhost:4040/fitness-users/password-update';

  constructor(private http:HttpClient) { }

  updateFitnessUserProfile(fitnessUser: object): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put(`${this.baseUrl}`, JSON.stringify(fitnessUser), {headers});
  }

  updateFitnessUserPassword(passwordObj: object): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put(`${this.updatePWUrl}`, JSON.stringify(passwordObj), {headers});
  }
}
