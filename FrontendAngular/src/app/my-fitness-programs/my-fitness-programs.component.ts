import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { PastProgramParticipationsService } from '../past-program-participations/past-program-participations.service';
import { MyFitnessProgramsService } from './my-fitness-programs.service';

@Component({
  selector: 'app-my-fitness-programs',
  templateUrl: './my-fitness-programs.component.html',
  styleUrl: './my-fitness-programs.component.css'
})
export class MyFitnessProgramsComponent {
  data: any = [];
  user: any;

  constructor( 
     private router: Router,
     private service: MyFitnessProgramsService) {

      this.readData();

  }

  readData() {
    var temp =  sessionStorage.getItem("user");
    if(temp) {
      this.user = JSON.parse(temp);
      this.service.baseUrl += this.user.id;
    }
      this.service.getMyPrograms().subscribe((data) => {
        this.data = data;
        console.log(data);
      },
      error => console.log(error));

    }

    deleteProgram(programId: any) {
      event?.preventDefault();
      this.service.deleteUrl+=programId+"/"+this.user.id;
      this.service.deleteProgram().subscribe((response) => {
        console.log(response);
        this.router.navigate(['/my-programs']);
      },
      error => console.log(error))
    }

}
