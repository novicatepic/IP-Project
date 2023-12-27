import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CheckCategorySubscriptionsService {

   //hard kodovano, kasnije autentifikacija
   private baseUrl = 'http://localhost:4040/category-subscriptions/subscribed/3';
  
   //hard kodovano
   deleteUrl = 'http://localhost:4040/category-subscriptions/unsubscribe/3/'

   constructor(private http:HttpClient) { }
 
   getSubscribedCategories(): Observable<any> {
     return this.http.get(`${this.baseUrl}`);
   }

   unsubscribeFromCategory(id: any): Observable<any> {
    this.deleteUrl+=id;
    //const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.delete(`${this.deleteUrl}`);
  }
}
