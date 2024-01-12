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
        console.log(data);
      },
      error => console.log(error));

    }

    deleteProgram(programId: any) {
      event?.preventDefault();
      this.service.deleteProgram(this.id, programId).subscribe((response) => {
        this.snackService.triggerSnackBar("Successfully deleted program!");
        this.router.navigate(['/my-fitness-programs']);
      },
      error => {
        console.log(error);
        this.snackService.triggerSnackBar("Error deleting program!");
      } )
    }

}
