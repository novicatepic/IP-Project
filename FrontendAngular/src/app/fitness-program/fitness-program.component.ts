import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'fitness-program',
  templateUrl: './fitness-program.component.html',
  styleUrl: './fitness-program.component.css'
})
export class FitnessProgramComponent {

  data : any = [];

  constructor(private http: HttpClient) {
    this.loadData();
  }

  loadData() {
    const url = `http://localhost:4040/fitness-programs`;
    this.http.get<any[]>(url).subscribe(data => {
      this.data = data;
      console.log(this.data);
    });
  }

}
