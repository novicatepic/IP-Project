import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CheckSingleMessageService {

  private baseUrl = 'http://localhost:4040/user-messages/';

  private readUrl = 'http://localhost:4040/user-messages/read-message/';

  private responseUrl = 'http://localhost:4040/user-messages';
  
  constructor(private http:HttpClient) { }

  getMessage(id: any, userId: any): Observable<any> {
    const url = this.baseUrl + id + "/" + userId;
    return this.http.get(`${url}`);
  }

  markMessageAsRead(id: any, userId : any): Observable<any> {
    const url = this.readUrl + id + "/" + userId;
    return this.http.get(`${url}`);
  }

  sendResponse(message: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.responseUrl}`, JSON.stringify(message), {headers});
  }
}
