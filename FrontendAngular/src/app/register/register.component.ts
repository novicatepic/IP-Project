import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RegisterService } from './register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  public firstForm : FormGroup
  question: any;

  constructor( 
    private formBuilder: FormBuilder,
     private router: Router,
     private service: RegisterService) {

    this.firstForm = formBuilder.group({
      firstName : [null, Validators.required],
      lastName : [null, Validators.required],
      city : [null, Validators.required],
      username : [null, Validators.required],
      password : [null, Validators.required],
      avatar : [null],
      email : [null, Validators.email],
    });
  }

  makeUser() {

    if(this.firstForm.valid) {
      const fitnessUser = {
        ime: this.firstForm.get('firstName')?.value,
        prezime: this.firstForm.get('lastName')?.value,
        grad: this.firstForm.get('city')?.value,
        korisnickoIme: this.firstForm.get('username')?.value,
        lozinka: this.firstForm.get('password')?.value,
        avatar: this.firstForm.get('avatar')?.value,
        mail: this.firstForm.get('email')?.value,
        aktivan: false
      }

      this.service.createFitnessUser(fitnessUser).subscribe((data) => {
        const user = JSON.stringify(data);
        console.log("Success: " + user);
        console.log("Success: " + data.id);
        this.router.navigate(['/code/'+data.id]);
      },
      error => console.log(error));

    }
  }
}
