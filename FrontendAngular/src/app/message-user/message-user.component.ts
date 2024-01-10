import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageUserService } from './message-user.service';
import { JwtTokenService } from '../jwt-token/jwt-token.service';
import { SnackBarService } from '../snack-bar/snack-bar.service';

@Component({
  selector: 'app-message-user',
  templateUrl: './message-user.component.html',
  styleUrl: './message-user.component.css'
})
export class MessageUserComponent {
  public firstForm : FormGroup
  question: any;
  id: any;
  users: any = [];

  constructor( 
    private formBuilder: FormBuilder,
     private router: Router,
     private service: MessageUserService,
     private jwtService: JwtTokenService, 
     private snackService: SnackBarService) {

      var temp = this.jwtService.extractTokenInfo();
      this.id = temp.id;
    this.getUsers();

    this.firstForm = formBuilder.group({
      selectedUser: [null, Validators.required],
      text : [null, [Validators.required, Validators.maxLength(1000)]]
    });
  }

  getUsers() {
      this.service.getUsers(this.id).subscribe((data) => {
          this.users = data;
      },
      error => {
        console.log(error);})
  }
  
  sendMessage() {
    if(this.firstForm.valid) {
      const message = {
        tekst: this.firstForm.get('text')?.value,
        procitana: false,
        korisnikId: this.id,
        posiljalacId: this.id,
        primalacId: this.firstForm.get('selectedUser')?.value
      }

      this.service.messageUser(message).subscribe((data) => {
          this.snackService.triggerSnackBar("Successfully sent!");
          this.router.navigate(['/message-user']);
      },
      error => {
        this.snackService.triggerSnackBar("Error sending message!");
        console.log(error);})

    }
  }
}
