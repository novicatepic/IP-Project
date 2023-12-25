import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MyFitnessProgramsService {

  baseUrl = 'http://localhost:4040/fitness-programs/my-programs/';
  deleteUrl = 'http://localhost:4040/fitness-programs/';
  
  constructor(private http:HttpClient) { }

  getMyPrograms(): Observable<any> {
    return this.http.get<any[]>(`${this.baseUrl}`);
  }

  deleteProgram(): Observable<any> {
    return this.http.delete(`${this.deleteUrl}`, { responseType: 'text' });
  }
}

