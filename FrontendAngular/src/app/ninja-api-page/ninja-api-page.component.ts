import { Component } from '@angular/core';
import { NinjaApiPageService } from './ninja-api-page.service';
import { HttpClient } from '@angular/common/http';
import { SnackBarService } from '../snack-bar/snack-bar.service';
import { Exercise } from '../model/Exercise';

@Component({
  selector: 'app-ninja-api-page',
  templateUrl: './ninja-api-page.component.html',
  styleUrl: './ninja-api-page.component.css'
})
export class NinjaApiPageComponent {
  data : Array<Exercise> = new Array();

  constructor(private snackService: SnackBarService, private service: NinjaApiPageService) {
    this.loadData();
  }

  loadData() {
    this.service.readNinjaApiExercises().subscribe((data) => {
      this.data = data;
    }, (err) => {
      this.snackService.triggerSnackBar("Error reading ninja API data!");
    })



  }
}
