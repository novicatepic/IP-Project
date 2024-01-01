import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CheckCategoryUnsubscribedService {
  private baseUrl = environment.categoryUnsubscriptionsBaseUrl;
  
  private subscribeUrl = environment.subscribeToCategoryUrl;

  constructor(private http:HttpClient) { }

  getUnsubscribedCategories(id : any): Observable<any> {
    const url = this.baseUrl + id;
    return this.http.get(`${url}`);
  }

  subscribeToCategory(entity: any): Observable<any> {

   const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
   return this.http.post(`${this.subscribeUrl}`, JSON.stringify(entity), {headers});
 }
}
