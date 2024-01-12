import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MyFitnessProgramsService {

  baseUrl = environment.myProgramsBaseUrl;
  deleteUrl = environment.deleteFitnessProgramsBaseUrl;
  
  constructor(private http:HttpClient) { }

  getMyPrograms(id:any): Observable<any> {
    const url = this.baseUrl + id;
    return this.http.get<any[]>(`${url}`);
  }

  deleteProgram(id :any, programId: any): Observable<any> {
    const url =  this.deleteUrl + programId + "/" + id;
    console.log("url " + url);
    return this.http.delete(`${url}`, { responseType: 'text' });
  }
}

