import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MessageConsultantService {

  private baseUrl = environment.messageConsultantUrl;
  
  constructor(private http:HttpClient) { }

  messageConsultant(message: object): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.baseUrl}`, JSON.stringify(message), {headers});
  }
}

