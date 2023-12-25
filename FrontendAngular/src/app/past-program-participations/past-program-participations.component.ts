import { Component } from '@angular/core';
import { PastProgramParticipationsService } from './past-program-participations.service';
import { Router } from '@angular/router';

@Component({
  selector: 'past-program-participations',
  templateUrl: './past-program-participations.component.html',
  styleUrl: './past-program-participations.component.css'
})
export class PastProgramParticipationsComponent {

  data: any = [];
  user: any;

  constructor( 
     private router: Router,
     private service: PastProgramParticipationsService) {

      this.readData();
      console.log("DATA PART: " + this.data);

  }

  readData() {
    var temp =  sessionStorage.getItem("user");
    if(temp) {
      this.user = JSON.parse(temp);
    }
    
    this.service.baseUrl += this.user.id;
      this.service.getPastParticipations().subscribe((data) => {
        this.data = data;
        console.log(data);
      },
      error => console.log(error));

    }
}
