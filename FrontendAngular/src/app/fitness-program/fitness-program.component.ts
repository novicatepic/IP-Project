import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'fitness-program',
  templateUrl: './fitness-program.component.html',
  styleUrl: './fitness-program.component.css'
})
export class FitnessProgramComponent implements OnInit {

  data: any = [];
  filteredData: any = [];
  pageSize = 3; // Number of items per page
  currentPage = 1; // Current page
  pages: number[] = [];
  @ViewChild('filterTextRef') filterTextRef!: ElementRef;
  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.loadData();
  }

  async updatePages() {
    const pageCount = Math.ceil(this.data.length / this.pageSize);
    this.pages = Array.from({ length: pageCount }, (_, index) => index + 1);
  }

  setPage(page: number) {
    if (page >= 1 && page <= this.pages.length) {
      this.currentPage = page;
    }
  }

  async loadData() {
    const url = `http://localhost:4040/fitness-programs`;

    try {
      this.data = await this.http.get<any[]>(url).toPromise();
      console.log(this.data);
      this.filteredData = this.data;  
      await this.updatePages(); // Await the updatePages call
    } catch (error) {
      console.error('Error loading data:', error);
    }
  }

  getCurrentPageData(): any[] {
    const startIndex = (this.currentPage - 1) * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    return this.filteredData.slice(startIndex, endIndex); //change
  }

  filter() {
    const filterValue = this.filterTextRef.nativeElement.value;
    this.filteredData = this.data.filter((item: any) => item.naziv.includes(filterValue));
    //console.log(this.filteredData);
  }

  filterByPrice() {
    this.filteredData.sort((a:any, b:any) => b.cijena - a.cijena);
  }

  filterByDifficulty() {
    this.filteredData.sort((a:any, b:any) => b.tezina - a.tezina);
  }

  filterByDuration() {
    this.filteredData.sort((a:any, b:any) => b.trajanje - a.trajanje);
  }

}
