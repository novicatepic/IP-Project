import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RegisterService } from './register.service';
import { SnackBarService } from '../snack-bar/snack-bar.service';

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
     private service: RegisterService,
     private snackService: SnackBarService) {

    this.firstForm = formBuilder.group({
      firstName : [null, [Validators.required, Validators.maxLength(45)]],
      lastName : [null, [Validators.required, Validators.maxLength(45)]],
      city : [null, [Validators.required, Validators.maxLength(100)]],
      username : [null, [Validators.required, Validators.maxLength(45)]],
      password : [null, [Validators.required, Validators.maxLength(100)]],
      avatar : [null],
      email : [null, [Validators.required, Validators.email, Validators.maxLength(200)]],
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
        this.snackService.triggerSnackBar("Code sent to mail: " + fitnessUser.mail);
        this.router.navigate(['/code/'+data.id]);
      },
      error => {
        console.log(error);
        this.snackService.triggerSnackBar("Error creating user!");
      } );

    }
  }
}
