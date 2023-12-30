import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { JwtTokenService } from '../jwt-token/jwt-token.service';
import { SingleFitnessProgramService } from './single-fitness-program.service';
import { SnackBarService } from '../snack-bar/snack-bar.service';

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
  photoUrls: string[] = [];
  imageUrl = '/static/uploads/12/image1.jpg';

  constructor(
    private http: HttpClient, 
    private route: ActivatedRoute, 
    private router : Router, 
    private location: Location, 
    private jwtService: JwtTokenService,
    private service: SingleFitnessProgramService,
    private snackService: SnackBarService) {
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });

    var temp = this.jwtService.extractTokenInfo();
    if(temp) {
      this.userId = temp.id;
    }
    this.loadData();
    this.loadQuestions();
    this.loadPhotos();
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

  loadPhotos() {
    this.service.loadPhotos(this.id).subscribe(
      (urls: any) => {
        this.photoUrls = urls;
        console.log("PHOTOS " + this.photoUrls);
      },
      (error) => {
        console.error('Error fetching photos:', error);
      }
    );
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
        this.snackService.triggerSnackBar("Comment successfully added!");
        this.router.navigate(['/fitness-programs/' + this.id]);
      }, (err) => {
        this.snackService.triggerSnackBar("Error adding comment!");
      });
    }
  }

}
