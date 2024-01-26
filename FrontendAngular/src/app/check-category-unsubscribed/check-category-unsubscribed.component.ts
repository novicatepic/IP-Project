import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CheckCategoryUnsubscribedService } from './check-category-unsubscribed.service';
import { JwtTokenService } from '../jwt-token/jwt-token.service';
import { SnackBarService } from '../snack-bar/snack-bar.service';

@Component({
  selector: 'app-check-category-unsubscribed',
  templateUrl: './check-category-unsubscribed.component.html',
  styleUrl: './check-category-unsubscribed.component.css'
})
export class CheckCategoryUnsubscribedComponent {
  data: any = [];
  user: any;

  constructor( 
     private router: Router,
     private service: CheckCategoryUnsubscribedService,
     private jwtService: JwtTokenService,
     private snackBarService: SnackBarService) {
      this.jwtService.getUserById().subscribe((data: any) => {
        this.user = data;
        this.getUnsubscribredCategories();
     });
      

  }

  getUnsubscribredCategories() {
      //this.service.baseUrl += this.user.id;
      this.service.getUnsubscribedCategories(this.user.id).subscribe((data) => {
          //console.log(data);
          this.data = data;
      },
      error => console.log(error))
  }

  subscribe(id: any) {
    const subscription = {
      korisnikId: this.user.id,
      kategorijaId: id
    }

    this.service.subscribeToCategory(subscription).subscribe((data) => {
      this.snackBarService.triggerSnackBar("Successfully subscribed!");  
      location.reload();
    }, (err) => {
      console.log(err);
      this.snackBarService.triggerSnackBar("Error subscribing!");
    });
  }
}
