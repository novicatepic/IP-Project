import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtTokenService } from '../jwt-token/jwt-token.service';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CheckCategorySubscriptionsService {

   private baseUrl = environment.categorySubscriptionsBaseUrl;
  
   private deleteUrl = environment.deleteCategorySubscription;

   constructor(private http:HttpClient) { 

   }
 
   getSubscribedCategories(id: any): Observable<any> {
    const url = this.baseUrl + id;
     return this.http.get(`${url}`);
   }

   unsubscribeFromCategory(tokenId:any, id: any): Observable<any> {
    const url = this.deleteUrl + tokenId + "/"+id;
    //const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.delete(`${url}`);
  }
}
