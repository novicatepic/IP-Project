import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NewJournalEntryService } from './new-journal-entry.service';

@Component({
  selector: 'app-new-journal-entry',
  templateUrl: './new-journal-entry.component.html',
  styleUrl: './new-journal-entry.component.css'
})
export class NewJournalEntryComponent {
  public firstForm : FormGroup
  question: any;

  constructor( 
    private formBuilder: FormBuilder,
     private router: Router,
     private service: NewJournalEntryService) {

    this.firstForm = formBuilder.group({
      exercise : [null, Validators.required],
      duration : [null, Validators.required],
      intensity : [null, Validators.required],
      weight : [null, Validators.required]
    });
  }

  addJournalEntry() {

    if(this.firstForm.valid) {
      const journalEntry = {
        vjezba: this.firstForm.get('exercise')?.value,
        trajanje: this.firstForm.get('duration')?.value,
        intenzitet: this.firstForm.get('intensity')?.value,
        kilaza: this.firstForm.get('weight')?.value,
        dnevnikKorisnikId: 3 //HARD KODOVAN KORISNIK ZA SAD
      }

      this.service.createJournalEntry(journalEntry).subscribe((data) => {
        //console.log("Success " + data)
        this.router.navigate(['/journal-entries']);
      },
      error => console.log(error));

    }
  }
}
