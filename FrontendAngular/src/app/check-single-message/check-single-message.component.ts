import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CheckSingleMessageService } from './check-single-message.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SnackBarService } from '../snack-bar/snack-bar.service';

@Component({
  selector: 'app-check-single-message',
  templateUrl: './check-single-message.component.html',
  styleUrl: './check-single-message.component.css'
})
export class CheckSingleMessageComponent {

  data: any;
  id: any;
  public firstForm : FormGroup

  constructor( 
     private router: Router,
     private service: CheckSingleMessageService, 
     private route: ActivatedRoute,
     private formBuilder: FormBuilder,
     private snackBarService: SnackBarService) {

      this.firstForm = formBuilder.group({
        response : [null,[Validators.required, Validators.maxLength(1000)]]
      });

      this.route.paramMap.subscribe(params => {
        this.id = params.get('id');
      });

      this.getMessage();

  }

  getMessage() {
    //this.service.baseUrl += this.id;
    
      this.service.getMessage(this.id).subscribe((data) => {
          this.data = data;
          //console.log(data);
          //this.service.markMessageAsRead().subscribe((readMessage) => {
            //console.log("read");
          //});
      },
      error => console.log(error))
  }

  respondToMessage() {
    //this.service.readUrl += this.id;
    if(this.firstForm.valid) {
      const newMessage = {
        tekst: this.firstForm.get('response')?.value,
        posiljalacId: this.data.primalac.id,
        primalacId: this.data.posiljalac.id,
        procitana: false
      }

      //console.log("new mssg" + JSON.stringify(newMessage));

      this.service.sendResponse(newMessage).subscribe((data) => {
        //console.log(data);
        this.service.markMessageAsRead(this.id).subscribe((readMessage) => {
            this.snackBarService.triggerSnackBar("Message sent!");
            this.router.navigate(['/all-messages']);
            //console.log("read");
        }, (err) => {
          console.log(err);
          this.snackBarService.triggerSnackBar("Error, message not sent!");
        });
      })
    }
  }

}
