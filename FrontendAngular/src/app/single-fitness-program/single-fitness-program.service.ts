import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SingleFitnessProgramService {

  private baseUrl = environment.picturesUrl;

  constructor(private http:HttpClient) { }

  loadPhotos(programId: any): Observable<string[]> {
    const url = `${this.baseUrl}${programId}`;
    return this.http.get<string[]>(url);
  }
}
