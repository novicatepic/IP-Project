import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtTokenService } from '../jwt-token/jwt-token.service';

@Injectable({
  providedIn: 'root'
})
export class CheckCategorySubscriptionsService {

   //hard kodovano, kasnije autentifikacija
   private baseUrl = 'http://localhost:4040/category-subscriptions/subscribed/';
  
   //hard kodovano
   private deleteUrl = 'http://localhost:4040/category-subscriptions/unsubscribe/'

   constructor(private http:HttpClient) { 

   }
 
   getSubscribedCategories(id: any): Observable<any> {
    const url = this.baseUrl + id;
     return this.http.get(`${url}`);
   }

   unsubscribeFromCategory(tokenId:any, id: any): Observable<any> {
    const url = this.deleteUrl + "/" + tokenId + "/"+id;
    //const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.delete(`${url}`);
  }
}
