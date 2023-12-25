import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BuyProgramService {

  baseUrl = 'http://localhost:4040/fitness-programs/unparticipated-user-programs/';
  
  constructor(private http:HttpClient) { }

  getUnparticipated(): Observable<any> {
    return this.http.get<any[]>(`${this.baseUrl}`);
  }
}
