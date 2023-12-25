import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessageConsultantService {

  private baseUrl = 'http://localhost:4040/messages';
  
  constructor(private http:HttpClient) { }

  messageConsultant(message: object): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.baseUrl}`, JSON.stringify(message), {headers});
  }
}

