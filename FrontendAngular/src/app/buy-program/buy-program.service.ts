import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BuyProgramService {

  baseUrl = 'http://localhost:4040/fitness-programs/unparticipated-user-programs/';

  private subscribeUrl = 'http://localhost:4040/fitness-programs/subscribe';
  
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
