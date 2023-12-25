import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, catchError, throwError } from 'rxjs';

@Component({
  selector: 'new-question',
  templateUrl: './new-question.component.html',
  styleUrl: './new-question.component.css'
})
export class NewQuestionComponent {
  public firstForm : FormGroup
  question: any;
  id: any;

  constructor(private http: HttpClient, 
    private formBuilder: FormBuilder,
     private route: ActivatedRoute,
     private router: Router) {
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });
    this.firstForm = formBuilder.group({
      question : [null, Validators.required]
    });
  }


  postQuestion()  {
    
    const url = `http://localhost:4040/questions`;
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    const questionControl = this.firstForm.get('question');

    if (questionControl && questionControl.value !== null) {
     const requestData = {
       tekst: questionControl.value,
       programId: parseInt(this.id, 10)
    };
    return this.http.post(`${url}`, JSON.stringify(requestData), {headers}).subscribe(data => {
      console.log(data);
      this.router.navigate(['/fitness-programs/'+this.id]);
    }
    );
  } else {
    console.error('Error: Question control or its value is null');
    return throwError('Error: Question control or its value is null');
  }
}

}
