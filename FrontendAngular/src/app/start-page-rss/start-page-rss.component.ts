import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { StartPageRssService } from './start-page-rss.service';
//const bcrypt = require('bcrypt');

const plainTextPassword = 'k1';

@Component({
  selector: 'start-page-rss',
  templateUrl: './start-page-rss.component.html',
  styleUrl: './start-page-rss.component.css'
})
export class StartPageRssComponent {

  data : any = [];

  constructor(private http: HttpClient, private service: StartPageRssService) {
    // Generate a salt and hash the password
  /*bcrypt.hash(plainTextPassword, 10, (err:any, hash:any) => {
    if (err) {
      console.error('Error hashing password:', err);
    } else {
      console.log('Bcrypt hash:', hash);
    }
});*/
    this.loadData();
  }

  loadData() {
    this.service.readRSSData().subscribe((data) => {
      this.data = data;
      console.log(data);
    })
  }

}
