import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessageUserService {

  private baseUrl = environment.messageUserUrl;
  
  private getAllButMe = environment.getAllButMe;

  constructor(private http:HttpClient) { }

  getUsers(id: any): Observable<any> {
    const url = this.getAllButMe + id;
    return this.http.get(`${url}`);
  }

  messageUser(message: object): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.baseUrl}`, JSON.stringify(message), {headers});
  }
}
