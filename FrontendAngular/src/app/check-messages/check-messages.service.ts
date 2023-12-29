import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtTokenService } from '../jwt-token/jwt-token.service';

@Injectable({
  providedIn: 'root'
})
export class CheckMessagesService {

  //hard kodovano, kasnije autentifikacija
  private baseUrl = 'http://localhost:4040/user-messages/unread/';
  id: any;
  constructor(private http:HttpClient, private jwtService: JwtTokenService) { 
      var temp = this.jwtService.extractTokenInfo();
      this.id = temp.id;
  }

  getUnreadMessages(): Observable<any> {
    this.baseUrl += this.id;
    return this.http.get(`${this.baseUrl}`);
  }
}
