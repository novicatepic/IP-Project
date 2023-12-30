import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../login/login.service';
import { ChangeProfileService } from './change-profile.service';
import { JwtTokenService } from '../jwt-token/jwt-token.service';
import { jwtDecode } from 'jwt-decode';
import { SnackBarService } from '../snack-bar/snack-bar.service';

@Component({
  selector: 'app-change-profile',
  templateUrl: './change-profile.component.html',
  styleUrl: './change-profile.component.css'
})
export class ChangeProfileComponent {
  public firstForm : FormGroup;
  public passwordForm : FormGroup;
  question: any;
  user: any;
  constructor( 
    private formBuilder: FormBuilder,
     private router: Router,
     private service: ChangeProfileService,
     private jwtService: JwtTokenService,
     private snackBarService: SnackBarService) {
      console.log("IN");
      this.jwtService.getUserById().subscribe((data: any) => {
         this.user = data;
         console.log( "USER="+this.user);
      });
      
      this.firstForm = formBuilder.group({
        firstName : [null, [Validators.required, Validators.maxLength(45)]],
        lastName : [null, [Validators.required, Validators.maxLength(45)]],
        city : [null, [Validators.required, Validators.maxLength(100)]],
        avatar : [null],
        email : [null, [Validators.required, Validators.email, Validators.maxLength(200)]],
      });

      this.passwordForm = formBuilder.group({
        oldPassword :[null, [Validators.required, Validators.maxLength(500)]],
        newPassword :[null, [Validators.required, Validators.maxLength(500)]],
        newPassword2 :[null, [Validators.required, Validators.maxLength(500)]],
      })
  }

  updatePassword() {
    if(this.passwordForm && this.passwordForm.valid) {
      const pwObject = {
        id: this.user.id, 
        oldPassword: this.passwordForm.get('oldPassword')?.value,
        newPassword: this.passwordForm.get('newPassword')?.value,
        newPassword2: this.passwordForm.get('newPassword2')?.value
      }

      if(pwObject.newPassword != pwObject.newPassword2) {
        this.snackBarService.triggerSnackBar("Passwords don't match!");
      } else {
        this.service.updateFitnessUserPassword(pwObject).subscribe((data) => {
          this.snackBarService.triggerSnackBar("Successfully updated password!");
          this.router.navigate(['/profile']);
        },
        error => console.log(error));
      }
    }
  }

  changeProfile() {

    if(this.firstForm && this.firstForm.valid) {
      const fitnessUser = {
        id: this.user.id, 
        korisnickoIme: this.user.korisnickoIme, 
        ime: this.firstForm.get('firstName')?.value,
        prezime: this.firstForm.get('lastName')?.value,
        grad: this.firstForm.get('city')?.value,
        avatar: this.firstForm.get('avatar')?.value,
        mail: this.firstForm.get('email')?.value,
        aktivan: this.user.aktivan 
      }

      this.service.updateFitnessUserProfile(fitnessUser).subscribe((data) => {
        this.snackBarService.triggerSnackBar("Successfully updated profile!");
        this.router.navigate(['/profile']);
      },
      error => {
        console.log(error);
        this.snackBarService.triggerSnackBar("Error updating profile!");
      } );

    }
  }
}
