import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CheckCategoryUnsubscribedService {
  //hard kodovano, kasnije autentifikacija
  baseUrl = 'http://localhost:4040/category-subscriptions/unsubscribed/';
  
  //hard kodovano
  private subscribeUrl = 'http://localhost:4040/category-subscriptions/subscribe'

  constructor(private http:HttpClient) { }

  getUnsubscribedCategories(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  subscribeToCategory(entity: any): Observable<any> {

   const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
   return this.http.post(`${this.subscribeUrl}`, JSON.stringify(entity), {headers});
 }
}
