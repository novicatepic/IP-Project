import { HttpClient } from '@angular/common/http';
import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { JournalEntriesService } from './journal-entries.service';
import { Chart, Legend, LineController, LineElement, PointElement, Title, Tooltip } from 'chart.js';
import { LinearScale, registerables } from 'chart.js';
import { SnackBarService } from '../snack-bar/snack-bar.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { JwtTokenService } from '../jwt-token/jwt-token.service';
import { NewJournalEntryService } from '../new-journal-entry/new-journal-entry.service';

@Component({
  selector: 'app-journal-entries',
  templateUrl: './journal-entries.component.html',
  styleUrl: './journal-entries.component.css'
})
export class JournalEntriesComponent implements OnInit, AfterViewInit {
  data : any = [];
  public firstForm : FormGroup;
  id: any;

  constructor(private http: HttpClient,
     private service: JournalEntriesService, 
     private snackBarService: SnackBarService,
     private router: Router, 
     private jwtService: JwtTokenService,
     private formBuilder : FormBuilder, 
     private entryService: NewJournalEntryService,
     private snackService : SnackBarService) {
    
      this.firstForm = formBuilder.group({
        exercise : [null, [Validators.required, Validators.maxLength(45)]],
        duration : [null, [Validators.required, Validators.maxLength(45)]],
        intensity : [null, [Validators.required, Validators.maxLength(45)]],
        weight : [null, [Validators.required, Validators.min(10), Validators.max(200)]],
        date : [null, [Validators.required]],
      });
      var temp = this.jwtService.extractTokenInfo();
      this.id = temp.id;
  }
  ngAfterViewInit(): void {
    this.loadDataAndCreateChart();
  }

  async loadDataAndCreateChart(): Promise<void> {
    await this.loadData();

    

    this.waitForData();
    this.createChart();
  }

  createChart(): void {
    this.data.sort((a:any, b:any) => {
      const dateA = new Date(a.datum);
      const dateB = new Date(b.datum);
    
      return dateA.getTime() - dateB.getTime();
    });
    this.data.sort((a:any, b:any) => a.datum - b.datum);
    const weightChartCanvas = document.getElementById('weight-chart') as HTMLCanvasElement | null;

    if (weightChartCanvas) {
      const ctx = weightChartCanvas.getContext('2d');
      if (ctx) {
        Chart.register(...registerables);
        const myChart = new Chart(ctx, {
          type: 'line',
          data: {
            labels: this.data.map((d: any) => d.datum),
            datasets: [
              {
                label: 'Weight Progression',
                data: this.data.map((d: any) => d.kilaza),
                borderColor: 'blue',
                backgroundColor: 'rgba(0, 123, 255, 0.1)',
                fill: true,
              },
            ],
          },
          options: {
            scales: {
              y: {
                beginAtZero: true,
              },
            },
          },
        });
      }
    }
  }

  delay(ms: number): Promise<void> {
    return new Promise((resolve) => setTimeout(resolve, ms));
  }

  private async waitForData(): Promise<void> {
    await this.delay(1000);
  }

  ngOnInit(): void {}

  loadData(): Promise<any> {
    return new Promise((resolve) => {
      this.service.readJournalEntriesForUser().subscribe((data: any) => {
        this.data = data;
        
        //console.log(this.data);
        resolve(this.data);
        
      });
    });
  }


  downloadPDF() {
    this.service.downloadPDF().subscribe(
      (data: Blob) => {
        const blob = new Blob([data], { type: 'application/pdf' });
        const link = document.createElement('a');
        link.href = window.URL.createObjectURL(blob);
        link.download = 'document.pdf';
        link.click();
      },
      error => {
        console.error('Error downloading PDF');
        this.snackBarService.triggerSnackBar("Error downloading pdf!");
      }
    );
  }

  deleteEntry(id: any) {
    this.service.deleteJournalEntry(id).subscribe((data:any) => {
      this.snackBarService.triggerSnackBar(data.text);
      location.reload();
    }, error => {
      console.log(error);
      this.snackBarService.triggerSnackBar("Error deleting journal entry!");
    })
    }



  async addJournalEntry() {
    document.getElementById("btnclose")?.click();
    await this.delay(1000);

    const currentDate = new Date();
    const selectedDate = new Date(this.firstForm.get('date')?.value);
    if(selectedDate <= currentDate) {
      if(this.firstForm.valid) {
        const journalEntry = {
          vjezba: this.firstForm.get('exercise')?.value,
          trajanje: this.firstForm.get('duration')?.value,
          intenzitet: this.firstForm.get('intensity')?.value,
          kilaza: this.firstForm.get('weight')?.value,
          dnevnikKorisnikId: this.id,
          datum: this.firstForm.get('date')?.value
        }
  
  
        this.entryService.createJournalEntry(journalEntry).subscribe((data: any) => {
          this.snackService.triggerSnackBar("Successfully created journal entry!");
          location.reload();
        },
        error => {
          console.log(error);
          this.snackService.triggerSnackBar("Error creating journal entry!");
        } );
  
      }
    } else {
      this.snackBarService.triggerSnackBar("Date must be in the past or present!");
    }
    
  }
}
