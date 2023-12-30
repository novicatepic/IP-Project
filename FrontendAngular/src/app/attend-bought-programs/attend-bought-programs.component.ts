import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AttendBoughtProgramsService } from './attend-bought-programs.service';
import { JwtTokenService } from '../jwt-token/jwt-token.service';

@Component({
  selector: 'app-attend-bought-programs',
  templateUrl: './attend-bought-programs.component.html',
  styleUrl: './attend-bought-programs.component.css'
})
export class AttendBoughtProgramsComponent {
  data: any = [];
  id: any;

  constructor( 
     private router: Router,
     private service: AttendBoughtProgramsService,
     private jwtService: JwtTokenService) {

      var temp = this.jwtService.extractTokenInfo();
      this.id = temp.id;

      this.readData();
      //console.log("DATA PART: " + this.data);

  }

  readData() {
    
      this.service.getCurrentParticipations(this.id).subscribe((data) => {
        this.data = data;
        //console.log("Locations " + JSON.stringify(this.data));
      },
      error => console.log(error));

    }
}
