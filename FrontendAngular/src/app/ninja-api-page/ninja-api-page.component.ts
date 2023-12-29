import { Component } from '@angular/core';
import { NinjaApiPageService } from './ninja-api-page.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-ninja-api-page',
  templateUrl: './ninja-api-page.component.html',
  styleUrl: './ninja-api-page.component.css'
})
export class NinjaApiPageComponent {
  data : any = [];

  constructor(private http: HttpClient, private service: NinjaApiPageService) {
    this.loadData();
  }

  loadData() {
    this.service.readNinjaApiExercises().subscribe((data) => {
      this.data = data;
      //console.log(data);
    })



  }
}
