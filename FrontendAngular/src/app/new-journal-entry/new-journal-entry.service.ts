import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NewJournalEntryService {

  private baseUrl = 'http://localhost:4040/journals/journal-entry';
  
  constructor(private http:HttpClient) { }

  createJournalEntry(journalEntry: object): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.baseUrl}`, JSON.stringify(journalEntry), {headers});
  }


}
