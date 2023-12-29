import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { JwtTokenService } from '../jwt-token/jwt-token.service';

@Component({
  selector: 'single-fitness-program',
  templateUrl: './single-fitness-program.component.html',
  styleUrl: './single-fitness-program.component.css'
})
export class SingleFitnessProgramComponent implements OnInit {

  data: any;
  questions: any[] = [];
  id: any;
  userId: any;

  constructor(private http: HttpClient, private route: ActivatedRoute, 
    private router : Router, private location: Location, private jwtService: JwtTokenService) {
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });

    var temp = this.jwtService.extractTokenInfo();
    this.userId = temp.id;

    this.loadData();
    this.loadQuestions();
  }
  ngOnInit(): void {
    
  }

  loadData() {
    const url = `http://localhost:4040/fitness-programs/${this.id}`;
    this.http.get<any>(url).subscribe(data => {
      this.data = data;
      //console.log(this.data);
    });
  }

  loadQuestions() {
    const url = `http://localhost:4040/questions/${this.id}`;
    this.http.get<any>(url).subscribe(data => {
      this.questions = data;
      //console.log("QUESTIONS=" + JSON.stringify(this.questions[0]));
    });
  }

  addComment(id: any) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const url = `http://localhost:4040/questions/respond`;
    var text = (document.getElementById(id) as HTMLInputElement)?.value;
    //console.log(text);
    if(text && text != "") {
      const answer = {
        odgovor: text,
        pitanjeId: id,
        korisnikId: this.userId
      }
      this.http.post(url, JSON.stringify(answer), {headers}).subscribe((data) => {
        //console.log("POSTED " + data);
        const currentUrl = this.router.url;
        this.router.navigate(['/fitness-programs/' + this.id]);
      });
    }
  }

}
