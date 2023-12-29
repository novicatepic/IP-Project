import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterService } from '../register/register.service';
import { CreateFitnessProgramService } from './create-fitness-program.service';
import { JwtTokenService } from '../jwt-token/jwt-token.service';

@Component({
  selector: 'create-fitness-program',
  templateUrl: './create-fitness-program.component.html',
  styleUrl: './create-fitness-program.component.css'
})
export class CreateFitnessProgramComponent {
  public firstForm : FormGroup
  question: any;
  categories: any;
  user: any;
  ytLink: any;
  constructor( 
    private formBuilder: FormBuilder,
     private router: Router,
     private service: CreateFitnessProgramService,
     private jwtService: JwtTokenService) {

      this.jwtService.getUserById().subscribe((data: any) => {
        this.user = data;
     });

      this.service.getCategories().subscribe((data) => {
        this.categories = data;
      })

    this.firstForm = formBuilder.group({
      name : [null, Validators.required],
      description : [null, Validators.required],
      price : [null, Validators.required],
      difficulty : [null, Validators.required],
      duration : [null, Validators.required],
      location : [null, Validators.required],
      contact : [null, Validators.required],
      date : [null, Validators.required],
      select : [null, Validators.required],
      ytlink : [null]
    });
  }

  createFitnessProgram() {
    if(this.firstForm.valid) {

      const fitnessProgram = {
        naziv: this.firstForm.get('name')?.value,
        opis: this.firstForm.get('description')?.value,
        cijena: this.firstForm.get('price')?.value,
        tezina: this.firstForm.get('difficulty')?.value,
        trajanje: this.firstForm.get('duration')?.value,
        kontakt: this.firstForm.get('contact')?.value,
        datum: this.firstForm.get('date')?.value,
        kreatorId: this.user.id,
        ucestvovan: false,
        kategorijaId: this.firstForm.get('select')?.value
      }

      const location = {
        nazivLokacije: this.firstForm.get('location')?.value,
        poruka: "",
        programId: 0
      }

      if(this.firstForm.get('location')?.value == 'Link') {
        location.poruka = this.firstForm.get('ytlink')?.value;
      }



      this.service.createFitnessProgram(fitnessProgram).subscribe((data)=> {
        //console.log(data);
        location.programId = data.id;
        this.service.createLocation(location).subscribe((data) => {
          console.log("Location " + location);
        });
      },
      error => console.log(error));

    }
  }
}
