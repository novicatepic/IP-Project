import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from './login.service';
import { JwtTokenService } from '../jwt-token/jwt-token.service';
import { ThisReceiver } from '@angular/compiler';

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
     private jwtService: JwtTokenService) {

    this.firstForm = formBuilder.group({
      username : [null, Validators.required],
      password : [null, Validators.required]
    });
  }

  login() {

    if(this.firstForm.valid) {
      const fitnessUser = {
        username: this.firstForm.get('username')?.value,
        password: this.firstForm.get('password')?.value
      }



      this.service.loginFitnessUser(fitnessUser).subscribe((data) => {
        console.log(JSON.stringify(data));
        if(data != null) {
          //console.log("SET INTO LOCAL STORAGE");
          localStorage.setItem("user", JSON.stringify(data));
          //console.log(this.jwtService.extractToken());
          this.router.navigate(['/fitness-programs']);
        } else {
          this.service.getByUsername(fitnessUser.username).subscribe((user) => {
            console.log(JSON.stringify(user));
            this.router.navigate(['/code/'+user.id]);
          })
          
        }
      },
      error => console.log("ERROR " + JSON.stringify(error)));

    }
  }
}
