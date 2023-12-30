import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CheckCategoryUnsubscribedService {
  //hard kodovano, kasnije autentifikacija
  private baseUrl = 'http://localhost:4040/category-subscriptions/unsubscribed/';
  
  //hard kodovano
  private subscribeUrl = 'http://localhost:4040/category-subscriptions/subscribe'

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
