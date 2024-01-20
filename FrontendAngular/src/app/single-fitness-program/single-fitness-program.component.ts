import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit, TemplateRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { JwtTokenService } from '../jwt-token/jwt-token.service';
import { SingleFitnessProgramService } from './single-fitness-program.service';
import { SnackBarService } from '../snack-bar/snack-bar.service';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { environment } from '../../environments/environment';


@Component({
  selector: 'single-fitness-program',
  templateUrl: './single-fitness-program.component.html',
  styleUrl: './single-fitness-program.component.css'
})
export class SingleFitnessProgramComponent {

  data: any;
  questions: any[] = [];
  id: any;
  userId: any;
  photoUrls: string[] = [];

  modalRef: BsModalRef | undefined;
  selectedImageUrl: string | undefined;

  constructor(
    private http: HttpClient, 
    private route: ActivatedRoute, 
    private router : Router, 
    private location: Location, 
    private jwtService: JwtTokenService,
    private service: SingleFitnessProgramService,
    private snackService: SnackBarService,
    private modalService: BsModalService) {
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
  
  openModal(template: TemplateRef<any>, imageUrl: string) {
    this.selectedImageUrl = imageUrl;
    this.modalRef = this.modalService.show(template);
  }

  loadData() {
    const programsUrl = environment.fitnessProgramsBaseUrl;
    const url = `${programsUrl}/${this.id}`;
    this.http.get<any>(url).subscribe(data => {
      this.data = data;
      const sqlDate = new Date(data.datum);
        if(sqlDate > new Date()) {
          this.data.aktivan = true;
        } else {
          this.data.aktivan = false;
        }
      //console.log(this.data.aktivan);
    });
  }

  loadQuestions() {
    const questionsUrl = environment.questionsUrl;
    const url = `${questionsUrl}/${this.id}`;
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
    const url = environment.respondQuestionUrl;
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
