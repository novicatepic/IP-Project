import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SingleFitnessProgramService {

  private baseUrl = 'http://localhost:4040/pictures/';

  constructor(private http:HttpClient) { }

  loadPhotos(programId: any): Observable<string[]> {
    const url = `${this.baseUrl}${programId}`;
    return this.http.get<string[]>(url);
  }
}
