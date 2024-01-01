import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AttendBoughtProgramsService {
  private baseUrl: string = environment.upcomingProgramsUrl;

  private locationsUrl = environment.locationsUrl;


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
