import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CheckCategorySubscriptionsService } from './check-category-subscriptions.service';
import { JwtTokenService } from '../jwt-token/jwt-token.service';
import { SnackBarService } from '../snack-bar/snack-bar.service';

@Component({
  selector: 'app-check-category-subscriptions',
  templateUrl: './check-category-subscriptions.component.html',
  styleUrl: './check-category-subscriptions.component.css'
})
export class CheckCategorySubscriptionsComponent {
  data: any = [];
  decodedTokenId: any;
  constructor( 
     private router: Router,
     private service: CheckCategorySubscriptionsService, 
     private jwtService: JwtTokenService,
     private snackBarService: SnackBarService) {

      var temp = this.jwtService.extractTokenInfo();
      this.decodedTokenId = temp.id;

      this.getSubscribredCategories();

  }

  getSubscribredCategories() {
      //this.service.baseUrl += this.decodedTokenId;
      this.service.getSubscribedCategories(this.decodedTokenId).subscribe((data) => {     
          this.data = data;
      },
      error => console.log(error))
  }

  unsubscribe(id: any) {
    this.service.unsubscribeFromCategory(this.decodedTokenId, id).subscribe((data) => {
      this.snackBarService.triggerSnackBar("Successfully unsubscribed!");
      this.router.navigate(['/category-unsubscribed']);
      //console.log(data);
    }, (err) => {
      console.log(err);
      this.snackBarService.triggerSnackBar("Error while trying to unsubscribe!");
    });
  }
}
