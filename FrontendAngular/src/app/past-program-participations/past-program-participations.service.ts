import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PastProgramParticipationsService {

  baseUrl = 'http://localhost:4040/fitness-programs/past-user-programs/';
  
  constructor(private http:HttpClient) { }

  getPastParticipations(): Observable<any> {
    return this.http.get<any[]>(`${this.baseUrl}`);
  }
}
