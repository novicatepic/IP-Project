import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BuyProgramService {

  private baseUrl: string = environment.buyProgramBaseUrl;

  private subscribeUrl = environment.subscribeUrl;
  
  constructor(private http:HttpClient) { }

  getUnparticipated(id: any): Observable<any> {
    const url = this.baseUrl + id;
    return this.http.get<any[]>(`${url}`);
  }

  subscribeToProgram(program: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.subscribeUrl}`, JSON.stringify(program), {headers});
  }
}
