import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CheckSingleMessageService {

  private baseUrl = environment.readUserMessagesUrl;

  private readUrl = environment.readMessageUrl;

  private responseUrl = environment.responseUrl;
  
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
