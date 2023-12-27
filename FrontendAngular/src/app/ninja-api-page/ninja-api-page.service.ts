import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NinjaApiPageService {

  private baseUrl = 'http://localhost:4040/api/exercises';
  
  constructor(private http:HttpClient) { }

  readNinjaApiExercises(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
