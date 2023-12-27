import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JournalEntriesService {

  //hard kodovana trica => id usera
  private baseUrl = 'http://localhost:4040/journals/journal-entry/3';
  
  constructor(private http:HttpClient) { }

  readJournalEntriesForUser(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
