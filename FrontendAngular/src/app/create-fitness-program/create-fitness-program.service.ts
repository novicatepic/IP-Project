import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CreateFitnessProgramService {

  private baseUrl = 'http://localhost:4040/fitness-programs';

  private categoryUrl = 'http://localhost:4040/categories';
  
  constructor(private http:HttpClient) { }

  createFitnessProgram(fitnessProgram: object): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.baseUrl}`, JSON.stringify(fitnessProgram), {headers});
  }

  getCategories(): Observable<any> {
    return this.http.get(`${this.categoryUrl}`);
  }
}
