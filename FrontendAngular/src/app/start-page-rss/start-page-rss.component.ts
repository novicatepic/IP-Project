import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { StartPageRssService } from './start-page-rss.service';
import { SnackBarService } from '../snack-bar/snack-bar.service';

const plainTextPassword = 'k1';

@Component({
  selector: 'start-page-rss',
  templateUrl: './start-page-rss.component.html',
  styleUrl: './start-page-rss.component.css'
})
export class StartPageRssComponent {

  data : any = [];

  constructor(private http: HttpClient, private service: StartPageRssService, private snackService: SnackBarService) {
    this.loadData();
  }

  loadData() {
    this.service.readRSSData().subscribe((data) => {
      this.data = data;
      //console.log(data);
    }, (err) => {
      this.snackService.triggerSnackBar("Error reading RSS data!");
    })
  }

}
