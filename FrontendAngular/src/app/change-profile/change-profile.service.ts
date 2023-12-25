import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChangeProfileService {

  private baseUrl = 'http://localhost:4040/fitness-users';
  
  constructor(private http:HttpClient) { }

  updateFitnessUserProfile(fitnessUser: object): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put(`${this.baseUrl}`, JSON.stringify(fitnessUser), {headers});
  }
}
