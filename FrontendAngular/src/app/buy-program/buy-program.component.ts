import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BuyProgramService } from './buy-program.service';

@Component({
  selector: 'app-buy-program',
  templateUrl: './buy-program.component.html',
  styleUrl: './buy-program.component.css'
})
export class BuyProgramComponent {
  data: any = [];
  user: any;

  constructor( 
     private router: Router,
     private service: BuyProgramService) {

      this.readData();
      console.log("DATA PART: " + this.data);

  }

  readData() {
    var temp =  sessionStorage.getItem("user");
    if(temp) {
      this.user = JSON.parse(temp);
    }
    
    this.service.baseUrl += this.user.id;
      this.service.getUnparticipated().subscribe((data) => {
        this.data = data;
        console.log(data);
      },
      error => console.log(error));

    }
}
