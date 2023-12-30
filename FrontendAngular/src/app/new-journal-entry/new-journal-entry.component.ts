import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NewJournalEntryService } from './new-journal-entry.service';
import { JwtTokenService } from '../jwt-token/jwt-token.service';
import { SnackBarService } from '../snack-bar/snack-bar.service';

@Component({
  selector: 'app-new-journal-entry',
  templateUrl: './new-journal-entry.component.html',
  styleUrl: './new-journal-entry.component.css'
})
export class NewJournalEntryComponent {
  public firstForm : FormGroup
  question: any;
  id:any;

  constructor( 
    private formBuilder: FormBuilder,
     private router: Router,
     private service: NewJournalEntryService,
     private jwtService: JwtTokenService,
     private snackService: SnackBarService) {

      var temp = this.jwtService.extractTokenInfo();
      this.id = temp.id;

    this.firstForm = formBuilder.group({
      exercise : [null, [Validators.required, Validators.maxLength(45)]],
      duration : [null, [Validators.required, Validators.maxLength(45)]],
      intensity : [null, [Validators.required, Validators.maxLength(45)]],
      weight : [null, [Validators.required, Validators.pattern("^[0-9]*$")]],
      date : [null, [Validators.required]],
    });
  }

  addJournalEntry() {

    if(this.firstForm.valid) {
      const journalEntry = {
        vjezba: this.firstForm.get('exercise')?.value,
        trajanje: this.firstForm.get('duration')?.value,
        intenzitet: this.firstForm.get('intensity')?.value,
        kilaza: this.firstForm.get('weight')?.value,
        dnevnikKorisnikId: this.id,
        datum: this.firstForm.get('date')?.value
      }


      this.service.createJournalEntry(journalEntry).subscribe((data) => {
        this.snackService.triggerSnackBar("Successfully created journal entry!");
        this.router.navigate(['/journal-entries']);
      },
      error => {
        console.log(error);
        this.snackService.triggerSnackBar("Error creating journal entry!");
      } );

    }
  }
}
