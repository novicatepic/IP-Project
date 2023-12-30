import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtTokenService } from '../jwt-token/jwt-token.service';

@Injectable({
  providedIn: 'root'
})
export class CheckMessagesService {

  private baseUrl = 'http://localhost:4040/user-messages/unread/';
  constructor(private http:HttpClient) { 
      
  }

  getUnreadMessages(id: any): Observable<any> {
    const url = this.baseUrl + id;
    return this.http.get(`${url}`);
  }
}
