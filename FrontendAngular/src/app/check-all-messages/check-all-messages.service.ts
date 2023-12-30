import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtTokenService } from '../jwt-token/jwt-token.service';

@Injectable({
  providedIn: 'root'
})
export class CheckAllMessagesService {

  private baseUrl = 'http://localhost:4040/user-messages/all/';
  
  constructor(private http:HttpClient) { 
  }

  getAllMessages(id: any): Observable<any> {
    const url = this.baseUrl + id;
    return this.http.get(`${url}`);
  }
}
