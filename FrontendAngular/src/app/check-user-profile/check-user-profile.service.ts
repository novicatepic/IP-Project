import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CheckUserProfileService {

  private baseUrl = environment.checkProfileUrl;
  constructor(private http:HttpClient) { 
      
  }

  getUserProfile(username: any): Observable<any> {
    const url = this.baseUrl + username;
    return this.http.get(`${url}`);
  }

}
