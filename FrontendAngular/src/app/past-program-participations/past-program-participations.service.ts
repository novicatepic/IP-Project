import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PastProgramParticipationsService {

  private baseUrl = environment.pastProgramParticipationsUrl;
  
  constructor(private http:HttpClient) { }

  getPastParticipations(id: any): Observable<any> {
    const url = this.baseUrl + id;
    return this.http.get<any[]>(`${url}`);
  }
}
