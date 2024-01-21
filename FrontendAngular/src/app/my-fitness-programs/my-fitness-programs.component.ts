import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { PastProgramParticipationsService } from '../past-program-participations/past-program-participations.service';
import { MyFitnessProgramsService } from './my-fitness-programs.service';
import { JwtTokenService } from '../jwt-token/jwt-token.service';
import { SnackBarService } from '../snack-bar/snack-bar.service';

@Component({
  selector: 'app-my-fitness-programs',
  templateUrl: './my-fitness-programs.component.html',
  styleUrl: './my-fitness-programs.component.css'
})
export class MyFitnessProgramsComponent {
  data: any = [];
  activePrograms: any = [];
  finishedPrograms: any = [];
  id: any;

  constructor( 
     private router: Router,
     private service: MyFitnessProgramsService,
     private jwtService: JwtTokenService,
     private snackService: SnackBarService) {

      var temp = this.jwtService.extractTokenInfo();
      this.id = temp.id;

      this.readData();

  }

  readData() {
      this.service.getMyPrograms(this.id).subscribe((data) => {
        this.data = data;
        this.data.forEach((entry: any) => {
          const sqlDate = new Date(entry.datum);
          if(sqlDate > new Date()) {
            entry.aktivan = true;
            this.activePrograms.push(entry);
          } else {
            entry.aktivan = false;
            this.finishedPrograms.push(entry);
          }
        });
        console.log(data);
      },
      error => console.log(error));

    }

    deleteProgram(programId: any) {
      event?.preventDefault();
      this.service.deleteProgram(this.id, programId).subscribe((response) => {
        this.snackService.triggerSnackBar("Successfully deleted program!");
        location.reload();
      },
      error => {
        console.log(error);
        this.snackService.triggerSnackBar("Error deleting program!");
      } )
    }

    showActive() {
      this.data = this.activePrograms;
    }

    showFinished() {
      this.data = this.finishedPrograms;
    }

}
