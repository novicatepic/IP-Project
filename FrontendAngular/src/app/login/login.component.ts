import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from './login.service';

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
     private service: LoginService) {

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
        console.log("DATA " + JSON.stringify(data));

        if(data) {
          this.router.navigate(['/fitness-programs']);
        } else {
          this.router.navigate(['/code/'+data.id]);
        }
      },
      error => console.log("ERROR " + JSON.stringify(error)));

    }
  }
}
