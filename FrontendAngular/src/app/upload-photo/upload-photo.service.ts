import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UploadPhotoService {

  private baseUrl = environment.uploadPhotoUrl;

  constructor(private http:HttpClient) { }

  uploadPhoto(formData: FormData, programId: any, userId: any): Observable<any> {
    const url = `${this.baseUrl}${programId}/${userId}`;
  const headers = new HttpHeaders();
  // Set Content-Type explicitly to handle multipart form data
  headers.set('Content-Type', 'multipart/form-data');
  return this.http.post(url, formData, { headers });
  }
}
