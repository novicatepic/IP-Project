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

  getMyPrograms(id:any): Observable<any> {
    const url = this.baseUrl + id;
    return this.http.get<any[]>(`${url}`);
  }

  deleteProgram(id :any): Observable<any> {
    const url = this.deleteUrl + id;
    return this.http.delete(`${url}`, { responseType: 'text' });
  }
}

