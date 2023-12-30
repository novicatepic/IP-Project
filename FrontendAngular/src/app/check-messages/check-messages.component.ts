import { Component } from '@angular/core';
import { CheckMessagesService } from './check-messages.service';
import { Router } from '@angular/router';
import { JwtTokenService } from '../jwt-token/jwt-token.service';

@Component({
  selector: 'app-check-messages',
  templateUrl: './check-messages.component.html',
  styleUrl: './check-messages.component.css'
})
export class CheckMessagesComponent {

  data: any = [];
  decodedTokenId: any;
  constructor( 
     private router: Router,
     private service: CheckMessagesService,
     private jwtService: JwtTokenService) {

      var temp = this.jwtService.extractTokenInfo();
      this.decodedTokenId = temp.id;

      this.getMyMessages();

  }

  getMyMessages() {

      this.service.getUnreadMessages(this.decodedTokenId).subscribe((data) => {
          this.data = data;
      },
      error => console.log(error))
  }

}
