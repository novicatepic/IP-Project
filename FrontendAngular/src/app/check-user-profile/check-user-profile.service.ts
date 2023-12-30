import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CheckUserProfileService {

  private baseUrl = 'http://localhost:4040/fitness-users/user/';
  constructor(private http:HttpClient) { 
      
  }

  getUserProfile(username: any): Observable<any> {
    const url = this.baseUrl + username;
    return this.http.get(`${url}`);
  }

}
