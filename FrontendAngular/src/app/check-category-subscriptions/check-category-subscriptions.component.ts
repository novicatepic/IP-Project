import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CheckCategorySubscriptionsService } from './check-category-subscriptions.service';
import { JwtTokenService } from '../jwt-token/jwt-token.service';

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
     private service: CheckCategorySubscriptionsService, private jwtService: JwtTokenService) {

      var temp = this.jwtService.extractTokenInfo();
      this.decodedTokenId = temp.id;

      console.log("TOKEN = " + this.decodedTokenId);

      this.getSubscribredCategories();

  }

  getSubscribredCategories() {
      //this.service.baseUrl += this.decodedTokenId;
      this.service.getSubscribedCategories(this.decodedTokenId).subscribe((data) => {
          //console.log(data);
          this.data = data;
      },
      error => console.log(error))
  }

  unsubscribe(id: any) {
    this.service.unsubscribeFromCategory(this.decodedTokenId, id).subscribe((data) => {
      this.router.navigate(['/category-subscriptions']);
      //console.log(data);
    });
  }
}
