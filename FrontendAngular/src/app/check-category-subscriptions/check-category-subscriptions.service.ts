import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtTokenService } from '../jwt-token/jwt-token.service';

@Injectable({
  providedIn: 'root'
})
export class CheckCategorySubscriptionsService {

   //hard kodovano, kasnije autentifikacija
   baseUrl = 'http://localhost:4040/category-subscriptions/subscribed/';
  
   //hard kodovano
   deleteUrl = 'http://localhost:4040/category-subscriptions/unsubscribe/'

   constructor(private http:HttpClient) { 

   }
 
   getSubscribedCategories(): Observable<any> {
     return this.http.get(`${this.baseUrl}`);
   }

   unsubscribeFromCategory(id: any): Observable<any> {
    this.deleteUrl+=id;
    //const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.delete(`${this.deleteUrl}`);
  }
}
