import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CheckSingleMessageService {

  baseUrl = 'http://localhost:4040/user-messages/';

  readUrl = 'http://localhost:4040/user-messages/read-message/';

  private responseUrl = 'http://localhost:4040/user-messages';
  
  constructor(private http:HttpClient) { }

  getMessage(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  markMessageAsRead(): Observable<any> {
    return this.http.get(`${this.readUrl}`);
  }

  sendResponse(message: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.responseUrl}`, JSON.stringify(message), {headers});
  }
}
