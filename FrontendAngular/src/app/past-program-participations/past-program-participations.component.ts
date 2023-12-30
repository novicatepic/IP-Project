import { Component } from '@angular/core';
import { PastProgramParticipationsService } from './past-program-participations.service';
import { Router } from '@angular/router';
import { JwtTokenService } from '../jwt-token/jwt-token.service';

@Component({
  selector: 'past-program-participations',
  templateUrl: './past-program-participations.component.html',
  styleUrl: './past-program-participations.component.css'
})
export class PastProgramParticipationsComponent {

  data: any = [];
  id: any;

  constructor( 
     private router: Router,
     private service: PastProgramParticipationsService,
     private jwtService: JwtTokenService) {

      var temp = this.jwtService.extractTokenInfo();
      this.id = temp.id;

      this.readData();
      //console.log("DATA PART: " + this.data);

  }

  readData() {
    
      this.service.getPastParticipations(this.id).subscribe((data) => {
        this.data = data;
        console.log(data);
      },
      error => {
        console.log(error);
        
      } );

    }
}
