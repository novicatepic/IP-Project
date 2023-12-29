import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtTokenService } from '../jwt-token/jwt-token.service';

@Injectable({
  providedIn: 'root'
})
export class CheckAllMessagesService {

  private baseUrl = 'http://localhost:4040/user-messages/all/';
  
  user : any;
  constructor(private http:HttpClient, private jwtService: JwtTokenService) { 
    this.jwtService.getUserById().subscribe((data: any) => {
      this.user = data;
      
   });
  }

  getAllMessages(): Observable<any> {
    this.baseUrl += this.user.id;
    return this.http.get(`${this.baseUrl}`);
  }
}
