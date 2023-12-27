import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { JournalEntriesService } from './journal-entries.service';

@Component({
  selector: 'app-journal-entries',
  templateUrl: './journal-entries.component.html',
  styleUrl: './journal-entries.component.css'
})
export class JournalEntriesComponent {
  data : any = [];

  constructor(private http: HttpClient, private service: JournalEntriesService) {
    this.loadData();
  }

  loadData() {
    this.service.readJournalEntriesForUser().subscribe((data) => {
      this.data = data;
      console.log(data);
    })

  }
}
