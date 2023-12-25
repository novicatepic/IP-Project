import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterService } from '../register/register.service';
import { CreateFitnessProgramService } from './create-fitness-program.service';

@Component({
  selector: 'create-fitness-program',
  templateUrl: './create-fitness-program.component.html',
  styleUrl: './create-fitness-program.component.css'
})
export class CreateFitnessProgramComponent {
  public firstForm : FormGroup
  question: any;
  user: any;
  constructor( 
    private formBuilder: FormBuilder,
     private router: Router,
     private service: CreateFitnessProgramService) {

      const userTemp = sessionStorage.getItem("user");
      console.log(userTemp);
      if(userTemp) {
        this.user = JSON.parse(userTemp);
      }

    this.firstForm = formBuilder.group({
      name : [null, Validators.required],
      description : [null, Validators.required],
      price : [null, Validators.required],
      difficulty : [null, Validators.required],
      duration : [null, Validators.required],
      location : [null, Validators.required],
      contact : [null, Validators.required],
      date : [null, Validators.required]
    });
  }

  createFitnessProgram() {
    console.log("in");
    if(this.firstForm.valid) {

      

      const fitnessProgram = {
        naziv: this.firstForm.get('name')?.value,
        opis: this.firstForm.get('description')?.value,
        cijena: this.firstForm.get('price')?.value,
        tezina: this.firstForm.get('difficulty')?.value,
        trajanje: this.firstForm.get('duration')?.value,
        lokacija: this.firstForm.get('location')?.value,
        kontakt: this.firstForm.get('contact')?.value,
        datum: this.firstForm.get('date')?.value,
        kreatorId: this.user.id,
        ucestvovan: false,
        aktivan: false
      }

      //console.log(JSON.stringify(fitnessProgram));

      this.service.createFitnessProgram(fitnessProgram).subscribe((data)=> {
        console.log(data);
      },
      error => console.log(error));

    }
  }
}
