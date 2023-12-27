import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { StartPageRssService } from './start-page-rss.service';

@Component({
  selector: 'start-page-rss',
  templateUrl: './start-page-rss.component.html',
  styleUrl: './start-page-rss.component.css'
})
export class StartPageRssComponent {

  data : any = [];

  constructor(private http: HttpClient, private service: StartPageRssService) {
    this.loadData();
  }

  loadData() {
    this.service.readRSSData().subscribe((data) => {
      this.data = data;
      console.log(data);
    })
  }

}
