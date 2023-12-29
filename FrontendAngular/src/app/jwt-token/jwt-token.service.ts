import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class JwtTokenService {

  private baseUrl = 'http://localhost:4040/fitness-users/';

  constructor(private http: HttpClient) { }

  extractToken() {
    var tokenTemp = localStorage.getItem("user");
    if(tokenTemp) {
      var token = JSON.parse(tokenTemp);
      return token.token;
    }
    return null;
  }

  extractTokenInfo() {
    var token = this.extractToken();
    if(token) {
      const decodedToken: any = jwtDecode(token);
      return decodedToken;
    }
    return null;
  }

  getUserById() : any {
    var tokenInfo = this.extractTokenInfo();
    if(tokenInfo) {
      this.baseUrl += tokenInfo.id;
      return this.http.get<any[]>(`${this.baseUrl}`);
    }
  }

  checkIfTokenExpired(token: any) {
    const decodedToken = this.extractTokenInfo();
    const expirationDate = decodedToken.exp * 1000; // Convert to milliseconds
    return expirationDate < Date.now();
  }

}
