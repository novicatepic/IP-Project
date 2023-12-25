import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CodeService } from './code.service';

@Component({
  selector: 'app-code',
  templateUrl: './code.component.html',
  styleUrl: './code.component.css'
})
export class CodeComponent {

  public firstForm : FormGroup
  question: any;
  id:any;

  constructor(
    private formBuilder: FormBuilder,
     private router: Router, 
     private service : CodeService,
     private route: ActivatedRoute) 
     
     {
    this.firstForm = formBuilder.group({
      code : [null, Validators.required]
    });
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });

    this.service.baseUrl += this.id;
   
  }

  inputCode() {
    if(this.firstForm.valid) {
      const code = {
        code: this.firstForm.get('code')?.value
      }

      this.service.insertCode(code).subscribe((data) => {
        const user = JSON.stringify(data);
        console.log("Success: " + data);
        console.log("Success: " + user);
        this.router.navigate(['/']);
      },
      error => console.log(error));
  }
}

}
