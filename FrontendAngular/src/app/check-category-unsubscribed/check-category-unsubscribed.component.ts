import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CheckCategoryUnsubscribedService } from './check-category-unsubscribed.service';

@Component({
  selector: 'app-check-category-unsubscribed',
  templateUrl: './check-category-unsubscribed.component.html',
  styleUrl: './check-category-unsubscribed.component.css'
})
export class CheckCategoryUnsubscribedComponent {
  data: any = [];
  constructor( 
     private router: Router,
     private service: CheckCategoryUnsubscribedService) {

      this.getUnsubscribredCategories();

  }

  getUnsubscribredCategories() {

      this.service.getUnsubscribedCategories().subscribe((data) => {
          //console.log(data);
          this.data = data;
      },
      error => console.log(error))
  }

  subscribe(id: any) {
    //hard-kodovano i ovdje
    const subscription = {
      korisnikId: 3,
      kategorijaId: id
    }

    this.service.subscribeToCategory(subscription).subscribe((data) => {
      this.router.navigate(['/category-unsubscribed']);
      console.log(data);
    });
  }
}
