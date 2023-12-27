import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CheckAllMessagesService {

  //hard kodovano, kasnije autentifikacija
  private baseUrl = 'http://localhost:4040/user-messages/all/3';
  
  constructor(private http:HttpClient) { }

  getAllMessages(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
