import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../login/login.service';
import { ChangeProfileService } from './change-profile.service';
import { JwtTokenService } from '../jwt-token/jwt-token.service';
import { jwtDecode } from 'jwt-decode';

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
     private jwtService: JwtTokenService) {
      console.log("IN");
      this.jwtService.getUserById().subscribe((data: any) => {
         this.user = data;
         console.log( "USER="+this.user);
      });
      
      this.firstForm = formBuilder.group({
        firstName : [null, Validators.required],
        lastName : [null, Validators.required],
        city : [null, Validators.required],
        //password : [null, Validators.required],
        avatar : [null],
        email : [null, [Validators.required, Validators.email]],
      });

      this.passwordForm = formBuilder.group({
        oldPassword :[null, Validators.required],
        newPassword :[null, Validators.required],
        newPassword2 :[null, Validators.required],
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

      console.log(pwObject);

      if(pwObject.newPassword != pwObject.newPassword2) {
        console.log("passwords don't match");
      } else {
        this.service.updateFitnessUserPassword(pwObject).subscribe((data) => {
          this.router.navigate(['/profile']);
        },
        error => console.log(error));
      }

      /*this.service.updateFitnessUserProfile(fitnessUser).subscribe((data) => {
        this.router.navigate(['/profile']);
      },
      error => console.log(error));*/

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
        //lozinka: this.firstForm.get('password')?.value,
        avatar: this.firstForm.get('avatar')?.value,
        mail: this.firstForm.get('email')?.value,
        aktivan: this.user.aktivan 
      }

      this.service.updateFitnessUserProfile(fitnessUser).subscribe((data) => {
        this.router.navigate(['/profile']);
      },
      error => console.log(error));

    }
  }
}
