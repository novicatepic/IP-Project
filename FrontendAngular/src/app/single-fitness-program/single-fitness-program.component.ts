import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'single-fitness-program',
  templateUrl: './single-fitness-program.component.html',
  styleUrl: './single-fitness-program.component.css'
})
export class SingleFitnessProgramComponent implements OnInit {

  data: any;
  questions: any[] = [];
  id: any;

  constructor(private http: HttpClient, private route: ActivatedRoute) {
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });
    this.loadData();
    this.loadQuestions();
  }
  ngOnInit(): void {
    
  }

  loadData() {
    const url = `http://localhost:4040/fitness-programs/${this.id}`;
    this.http.get<any>(url).subscribe(data => {
      this.data = data;
      console.log(this.data);
    });
  }

  loadQuestions() {
    const url = `http://localhost:4040/questions/${this.id}`;
    this.http.get<any>(url).subscribe(data => {
      this.questions = data;
      console.log("QUESTIONS=" + this.questions);
    });
  }



}
