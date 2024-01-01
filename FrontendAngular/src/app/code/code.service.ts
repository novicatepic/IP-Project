import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CodeService {

  private baseUrl = environment.insertCodeUrl;
  constructor(private http:HttpClient) {
    
  }

  insertCode(code: object, id:any): Observable<any> {
    const url = this.baseUrl + id;
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${url}`, JSON.stringify(code), {headers});
  }
}
