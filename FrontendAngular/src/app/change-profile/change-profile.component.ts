import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../login/login.service';
import { ChangeProfileService } from './change-profile.service';

@Component({
  selector: 'app-change-profile',
  templateUrl: './change-profile.component.html',
  styleUrl: './change-profile.component.css'
})
export class ChangeProfileComponent {
  public firstForm : FormGroup
  question: any;
  user: any;
  constructor( 
    private formBuilder: FormBuilder,
     private router: Router,
     private service: ChangeProfileService) {
      var data =  sessionStorage.getItem("user");
      if(data) {
        this.user = JSON.parse(data);
      } else {
        this.router.navigate(['fitness-programs']);
      }

      this.firstForm = formBuilder.group({
        firstName : [null, Validators.required],
        lastName : [null, Validators.required],
        city : [null, Validators.required],
        password : [null, Validators.required],
        avatar : [null],
        email : [null, Validators.email],
      });
  }

  changeProfile() {

    if(this.firstForm.valid) {
      const fitnessUser = {
        id: this.user.id,
        korisnickoIme: this.user.korisnickoIme,
        ime: this.firstForm.get('firstName')?.value,
        prezime: this.firstForm.get('lastName')?.value,
        grad: this.firstForm.get('city')?.value,
        lozinka: this.firstForm.get('password')?.value,
        avatar: this.firstForm.get('avatar')?.value,
        mail: this.firstForm.get('email')?.value,
        aktivan: this.user.aktivan
      }

      this.service.updateFitnessUserProfile(fitnessUser).subscribe((data) => {
        const user = JSON.stringify(data);
        console.log("Success: " + user);
        console.log("Success: " + data.id);
        console.log("Success: " + data.aktivan);

        sessionStorage.setItem("user", JSON.stringify(fitnessUser));
        this.router.navigate(['/change-profile']);
      },
      error => console.log(error));

    }
  }
}
