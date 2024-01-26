import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CodeService } from './code.service';
import { SnackBarService } from '../snack-bar/snack-bar.service';
import { Code } from '../model/Code';
import { AuthServiceService } from '../auth-service/auth-service.service';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-code',
  templateUrl: './code.component.html',
  styleUrl: './code.component.css'
})
export class CodeComponent {

  public firstForm : FormGroup
  question: any;
  id:any;

  private key = environment.storageKey;

  constructor(
    private formBuilder: FormBuilder,
     private router: Router, 
     private service : CodeService,
     private route: ActivatedRoute,
     private snackBarService: SnackBarService,
     private authService: AuthServiceService) 
     
     {
    this.firstForm = formBuilder.group({
      code : [null, [Validators.required, Validators.maxLength(4), Validators.minLength(4)]]
    });
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });

    //this.service.baseUrl += this.id;
   
  }

  inputCode() {
    if(this.firstForm.valid) {
      /*const code = {
        code: this.firstForm.get('code')?.value
      }*/

      let value = this.firstForm.get('code')?.value;
      const code = new Code(value);

      this.service.insertCode(code, this.id).subscribe((data) => {
        const user = JSON.stringify(data);
        /*console.log("Success: " + data);
        console.log("Success: " + user);*/

        localStorage.setItem(this.key, user);
        this.snackBarService.triggerSnackBar("Successfully activated profile!");
        this.authService.notifyLoginSuccess();
        this.router.navigate(['/']);
      },
      error => {
        console.log(error);
        this.snackBarService.triggerSnackBar("Error activating profile!");
      } );
  }
}

}
