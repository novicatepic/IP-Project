import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NewJournalEntryService {

  private baseUrl = environment.newJournalEntryUrl;
  
  constructor(private http:HttpClient) { }

  createJournalEntry(journalEntry: object): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.baseUrl}`, JSON.stringify(journalEntry), {headers});
  }


}
