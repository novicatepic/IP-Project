import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterService } from '../register/register.service';
import { MessageConsultantService } from './message-consultant.service';

@Component({
  selector: 'app-message-consultant',
  templateUrl: './message-consultant.component.html',
  styleUrl: './message-consultant.component.css'
})
export class MessageConsultantComponent {

  public firstForm : FormGroup
  question: any;
  user: any;
  constructor( 
    private formBuilder: FormBuilder,
     private router: Router,
     private service: MessageConsultantService) {



    this.firstForm = formBuilder.group({
      title: [null, Validators.required],
      text : [null, Validators.required]
    });
  }

  sendMessage() {
    if(this.firstForm.valid) {
      const message = {
        naslov: this.firstForm.get('title')?.value,
        tekst: this.firstForm.get('text')?.value,
        procitana: false,
        odgovorena: false,
        datum: new Date(),
        korisnikId: 1
      }

      this.service.messageConsultant(message).subscribe((data) => {
          console.log(data);
      },
      error => console.log(error))

      /*this.service.createFitnessUser(fitnessUser).subscribe((data) => {
        const user = JSON.stringify(data);
        console.log("Success: " + user);
        console.log("Success: " + data.id);
        this.router.navigate(['/code/'+data.id]);
      },
      error => console.log(error));*/

    }
  }

}
