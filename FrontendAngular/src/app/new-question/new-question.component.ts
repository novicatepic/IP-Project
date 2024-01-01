import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, catchError, throwError } from 'rxjs';
import { JwtTokenService } from '../jwt-token/jwt-token.service';
import { SnackBarService } from '../snack-bar/snack-bar.service';
import { environment } from '../../environments/environment';

@Component({
  selector: 'new-question',
  templateUrl: './new-question.component.html',
  styleUrl: './new-question.component.css'
})
export class NewQuestionComponent {
  public firstForm : FormGroup
  question: any;
  id: any;
  userId: any;

  constructor(private http: HttpClient, 
    private formBuilder: FormBuilder,
     private route: ActivatedRoute,
     private router: Router,
     private jwtService: JwtTokenService,
     private snackService: SnackBarService) {
      var temp = this.jwtService.extractTokenInfo();
      this.userId = temp.id;

    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });
    this.firstForm = formBuilder.group({
      question : [null, [Validators.required, Validators.maxLength(1000)]]
    });
  }


  postQuestion()  {
    
    const url = environment.questionsUrl;
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    const questionControl = this.firstForm.get('question');

    if (questionControl && questionControl.value !== null) {
     const requestData = {
       tekst: questionControl.value,
       programId: parseInt(this.id, 10),
       korisnikId: this.userId
    };
    this.http.post(`${url}`, JSON.stringify(requestData), {headers}).subscribe(data => {
      this.snackService.triggerSnackBar("Comment posted!");
      this.router.navigate(['/fitness-programs/'+this.id]);
    }
    );
  } else {
    console.error('Error: Question control or its value is null');
    this.snackService.triggerSnackBar("Error posting comment!");
  }
}

}
