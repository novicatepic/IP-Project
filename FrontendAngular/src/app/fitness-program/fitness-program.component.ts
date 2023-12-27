import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'fitness-program',
  templateUrl: './fitness-program.component.html',
  styleUrl: './fitness-program.component.css'
})
export class FitnessProgramComponent implements OnInit {

  data: any = [];
  pageSize = 3; // Number of items per page
  currentPage = 1; // Current page
  pages: number[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.loadData();
  }

  async updatePages() {
    console.log(this.data);
    const pageCount = Math.ceil(this.data.length / this.pageSize);
    this.pages = Array.from({ length: pageCount }, (_, index) => index + 1);
  }

  setPage(page: number) {
    if (page >= 1 && page <= this.pages.length) {
      this.currentPage = page;
      console.log("curr page " + this.currentPage);
    }
  }

  async loadData() {
    const url = `http://localhost:4040/fitness-programs`;

    try {
      this.data = await this.http.get<any[]>(url).toPromise();
      await this.updatePages(); // Await the updatePages call
    } catch (error) {
      console.error('Error loading data:', error);
    }
  }

  getCurrentPageData(): any[] {
    const startIndex = (this.currentPage - 1) * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    return this.data.slice(startIndex, endIndex);
  }

}
