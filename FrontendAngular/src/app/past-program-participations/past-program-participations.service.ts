import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PastProgramParticipationsService {

  private baseUrl = 'http://localhost:4040/fitness-programs/past-user-programs/';
  
  constructor(private http:HttpClient) { }

  getPastParticipations(id: any): Observable<any> {
    const url = this.baseUrl + id;
    return this.http.get<any[]>(`${url}`);
  }
}
