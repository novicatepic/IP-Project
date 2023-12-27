import { HttpClient } from '@angular/common/http';
import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { JournalEntriesService } from './journal-entries.service';
import { Chart, Legend, LineController, LineElement, PointElement, Title, Tooltip } from 'chart.js';
import { LinearScale, registerables } from 'chart.js';

@Component({
  selector: 'app-journal-entries',
  templateUrl: './journal-entries.component.html',
  styleUrl: './journal-entries.component.css'
})
export class JournalEntriesComponent implements OnInit, AfterViewInit {
  data : any = [];


  constructor(private http: HttpClient, private service: JournalEntriesService) {
    
    
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
    const weightChartCanvas = document.getElementById('weight-chart') as HTMLCanvasElement | null;

    if (weightChartCanvas) {
      //console.log('IN');
      const ctx = weightChartCanvas.getContext('2d');
      if (ctx) {
        Chart.register(...registerables);
        const myChart = new Chart(ctx, {
          type: 'line',
          data: {
            labels: this.data.map((d: any) => d.trajanje),
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
      }
    );
  }
}
