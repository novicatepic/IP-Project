import { Component } from '@angular/core';
import { CheckMessagesService } from './check-messages.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-check-messages',
  templateUrl: './check-messages.component.html',
  styleUrl: './check-messages.component.css'
})
export class CheckMessagesComponent {

  data: any = [];
  constructor( 
     private router: Router,
     private service: CheckMessagesService) {

      this.getMyMessages();

  }

  getMyMessages() {

      this.service.getUnreadMessages().subscribe((data) => {
          //console.log(data);
          this.data = data;
      },
      error => console.log(error))
  }

}
