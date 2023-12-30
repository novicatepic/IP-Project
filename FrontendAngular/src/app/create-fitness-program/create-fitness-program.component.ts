import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterService } from '../register/register.service';
import { CreateFitnessProgramService } from './create-fitness-program.service';
import { JwtTokenService } from '../jwt-token/jwt-token.service';
import { SnackBarService } from '../snack-bar/snack-bar.service';

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
     private jwtService: JwtTokenService,
     private snackBarService: SnackBarService) {

      this.jwtService.getUserById().subscribe((data: any) => {
        this.user = data;
     });

      this.service.getCategories().subscribe((data) => {
        this.categories = data;
      })

    this.firstForm = formBuilder.group({
      name : [null, [Validators.required, Validators.maxLength(200)]],
      description : [null, [Validators.required, Validators.maxLength(2000)]],
      price : [null, [Validators.required, Validators.pattern("^[0-9]*$")]],
      difficulty : [null, [Validators.required, Validators.maxLength(100)]],
      duration : [null, [Validators.required, Validators.pattern("^[0-9]*$")]],
      location : [null, [Validators.required, Validators.maxLength(200)]],
      contact : [null, [Validators.required, Validators.maxLength(45)]],
      date : [null, [Validators.required]],
      select : [null, [Validators.required, Validators.maxLength(45)]],
      ytlink : [null, [Validators.required, Validators.maxLength(200)]]
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
        kategorijaId: this.firstForm.get('select')?.value,
        nazivLokacije: this.firstForm.get('location')?.value,
        porukaLokacije: this.firstForm.get('ytlink')?.value
      }


      this.service.createFitnessProgram(fitnessProgram).subscribe((data)=> {
        this.snackBarService.triggerSnackBar("Fitness program created!");
        this.router.navigate(['/my-fitness-programs']);
      },
      error => {
        console.log(error);
        this.snackBarService.triggerSnackBar("Error creating fitness program!");
      } );

    }
  }
}
