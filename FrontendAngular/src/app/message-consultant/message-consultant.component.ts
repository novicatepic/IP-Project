import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterService } from '../register/register.service';
import { MessageConsultantService } from './message-consultant.service';
import { JwtTokenService } from '../jwt-token/jwt-token.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SnackBarService } from '../snack-bar/snack-bar.service';

@Component({
  selector: 'app-message-consultant',
  templateUrl: './message-consultant.component.html',
  styleUrl: './message-consultant.component.css'
})
export class MessageConsultantComponent {

  public firstForm : FormGroup
  question: any;
  id: any;
  constructor( 
    private formBuilder: FormBuilder,
     private router: Router,
     private service: MessageConsultantService,
     private jwtService: JwtTokenService, 
     private snackService: SnackBarService) {

      var temp = this.jwtService.extractTokenInfo();
      this.id = temp.id;

    this.firstForm = formBuilder.group({
      title: [null, [Validators.required, Validators.maxLength(100)]],
      text : [null, [Validators.required, Validators.maxLength(1000)]]
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
        korisnikId: this.id
      }

      this.service.messageConsultant(message).subscribe((data) => {
          this.snackService.triggerSnackBar("Successfully sent!");
          this.firstForm.reset();
          this.router.navigate(['/message-consultant']);
      },
      error => {
        this.snackService.triggerSnackBar("Error sending message!");
        console.log(error);})

    }
  }

}
