import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { JwtTokenService } from '../jwt-token/jwt-token.service';
import { UploadPhotoService } from './upload-photo.service';
import { SnackBarService } from '../snack-bar/snack-bar.service';

@Component({
  selector: 'app-upload-photo',
  templateUrl: './upload-photo.component.html',
  styleUrl: './upload-photo.component.css'
})
export class UploadPhotoComponent {
  selectedFile: File | null = null;

  programId : any;
  userId: any;

  constructor(private route: ActivatedRoute, 
    private router : Router, private jwtService: JwtTokenService, 
    private photoService : UploadPhotoService,
    private snackService: SnackBarService) {
    this.route.paramMap.subscribe(params => {
      this.programId = params.get('programId');
    });

    var temp = this.jwtService.extractTokenInfo();
    this.userId = temp.id;
  }

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0] as File;
  }

  onSubmit(): void {
    if (this.selectedFile) {
      const formData = new FormData();
      formData.append('file', this.selectedFile);

      this.photoService.uploadPhoto(formData, this.programId).subscribe(
        (response) => {
          this.snackService.triggerSnackBar("Photo uploaded successfully!");
          console.log('File uploaded successfully:', response);
        },
        (error) => {
          this.snackService.triggerSnackBar("Error uploading photo!");
          console.error('Error uploading file:', error);
        }
      );
    }
  }
}
