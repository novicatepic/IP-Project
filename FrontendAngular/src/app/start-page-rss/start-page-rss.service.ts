import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StartPageRssService {

  private baseUrl = 'http://localhost:4040/rss';
  
  constructor(private http:HttpClient) { }

  readRSSData(): Observable<any> {
    //const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.get(`${this.baseUrl}`);
  }
}
