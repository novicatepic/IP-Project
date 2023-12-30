import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtTokenService } from '../jwt-token/jwt-token.service';

@Injectable({
  providedIn: 'root'
})
export class JournalEntriesService {

  private baseUrl = 'http://localhost:4040/journals/journal-entry/';

  private pdfUrl = 'http://localhost:4040/journals/download-journal/';


  id : any;
  constructor(private http:HttpClient, private jwtService: JwtTokenService) {
    var temp = this.jwtService.extractTokenInfo();
    this.id = temp.id;
   }

   readJournalEntriesForUser(): Observable<any> {
    const url = this.baseUrl + this.id;
    return this.http.get(`${url}`);
  }

  downloadPDF() {
    const url = this.pdfUrl + this.id;
    return this.http.get(`${url}`, { responseType: 'blob' });
  }

  deleteJournalEntry(entryId: any) {
    const url = this.baseUrl + entryId + "/" + this.id;
    return this.http.delete(`${url}`);
  }
}
