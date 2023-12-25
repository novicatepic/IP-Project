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
        korisnickoIme: this.firstForm.get('username')?.value,
        lozinka: this.firstForm.get('password')?.value
      }

      this.service.loginFitnessUser(fitnessUser).subscribe((data) => {
        const user = JSON.stringify(data);
        console.log("Success: " + user);
        console.log("Success: " + data.id);
        console.log("Success: " + data.aktivan);

        if(data.aktivan == true) {
          sessionStorage.removeItem("user");
          sessionStorage.setItem("user", user);
          this.router.navigate(['/fitness-programs']);
        } else {
          this.router.navigate(['/code/'+data.id]);
        }
      },
      error => console.log(error));

    }
  }
}
