import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CheckAllMessagesService } from './check-all-messages.service';
import { JwtTokenService } from '../jwt-token/jwt-token.service';

@Component({
  selector: 'app-check-all-messages',
  templateUrl: './check-all-messages.component.html',
  styleUrl: './check-all-messages.component.css'
})
export class CheckAllMessagesComponent {
  data: any = [];

  decodedTokenId: any;

  constructor( 
     private router: Router,
     private service: CheckAllMessagesService,
     private jwtService : JwtTokenService) {
      var temp = this.jwtService.extractTokenInfo();
      this.decodedTokenId = temp.id;
      this.getMyMessages();

  }

  getMyMessages() {

      this.service.getAllMessages(this.decodedTokenId).subscribe((data) => {
          //console.log(data);
          this.data = data;
      },
      error => console.log(error))
  }
}
