import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from './login.service';
import { JwtTokenService } from '../jwt-token/jwt-token.service';
import { ThisReceiver } from '@angular/compiler';
import { Observable, Subject } from 'rxjs';
import { AuthService } from '../interceptors/auth.service';
import { AuthServiceService } from '../auth-service/auth-service.service';
import { SnackBarService } from '../snack-bar/snack-bar.service';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  public firstForm : FormGroup
  question: any;



  constructor( 
    private formBuilder: FormBuilder,
     private router: Router,
     private service: LoginService,
     private jwtService: JwtTokenService,
     private authService: AuthServiceService,
     private snackBarService: SnackBarService) {

    this.firstForm = formBuilder.group({
      username : [null, [Validators.required, Validators.maxLength(45)]],
      password : [null, [Validators.required, Validators.maxLength(500)]]
    });
  }

  login() {

    if(this.firstForm.valid) {
      const fitnessUser = {
        username: this.firstForm.get('username')?.value,
        password: this.firstForm.get('password')?.value
      }



      this.service.loginFitnessUser(fitnessUser).subscribe((data) => {
        console.log("Data = " + JSON.stringify(data));
        if(data != null) {
          localStorage.setItem("user", JSON.stringify(data));
          this.authService.notifyLoginSuccess();
          this.router.navigate(['/fitness-programs']);
        } else if(data!=null && data.terminated) {
          this.snackBarService.triggerSnackBar("Your account has been terminated by the administrator!");
        } 
        else {
          this.service.getByUsername(fitnessUser.username).subscribe((user) => {
            this.snackBarService.triggerSnackBar("Code sent to your email: " + user.mail);
            //console.log(JSON.stringify(user));
            this.router.navigate(['/code/'+user.id]);
          })
          
        }
      },
      (error : HttpResponse<any>) => {
        if(error.status === 403) {
          this.snackBarService.triggerSnackBar("Terminated account or incorrect credentials!");
        } 
        
      } );

    }
  }
}
