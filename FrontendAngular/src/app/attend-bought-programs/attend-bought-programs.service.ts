import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AttendBoughtProgramsService {

  private baseUrl = 'http://localhost:4040/fitness-programs/upcoming-user-programs/';

  private locationsUrl = 'http://localhost:4040/locations/';
  
  constructor(private http:HttpClient) { }

  getCurrentParticipations(id: any): Observable<any> {
    const url = this.baseUrl + id;
    return this.http.get<any[]>(`${url}`);
  }

  getLocations(id: any): Observable<any> {
    const url = this.locationsUrl + id;
    return this.http.get<any[]>(`${url}`);
  }

}
