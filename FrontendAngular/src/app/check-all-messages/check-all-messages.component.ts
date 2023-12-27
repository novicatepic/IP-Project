import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CheckAllMessagesService } from './check-all-messages.service';

@Component({
  selector: 'app-check-all-messages',
  templateUrl: './check-all-messages.component.html',
  styleUrl: './check-all-messages.component.css'
})
export class CheckAllMessagesComponent {
  data: any = [];
  constructor( 
     private router: Router,
     private service: CheckAllMessagesService) {

      this.getMyMessages();

  }

  getMyMessages() {

      this.service.getAllMessages().subscribe((data) => {
          //console.log(data);
          this.data = data;
      },
      error => console.log(error))
  }
}
