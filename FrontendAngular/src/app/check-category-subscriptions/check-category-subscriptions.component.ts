import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CheckCategorySubscriptionsService } from './check-category-subscriptions.service';

@Component({
  selector: 'app-check-category-subscriptions',
  templateUrl: './check-category-subscriptions.component.html',
  styleUrl: './check-category-subscriptions.component.css'
})
export class CheckCategorySubscriptionsComponent {
  data: any = [];
  constructor( 
     private router: Router,
     private service: CheckCategorySubscriptionsService) {

      this.getSubscribredCategories();

  }

  getSubscribredCategories() {

      this.service.getSubscribedCategories().subscribe((data) => {
          //console.log(data);
          this.data = data;
      },
      error => console.log(error))
  }

  unsubscribe(id: any) {
    this.service.unsubscribeFromCategory(id).subscribe((data) => {
      this.router.navigate(['/category-subscriptions']);
      console.log(data);
    });
  }
}
