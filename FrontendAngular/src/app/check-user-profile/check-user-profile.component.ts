import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CheckUserProfileService } from './check-user-profile.service';
import { JwtTokenService } from '../jwt-token/jwt-token.service';

@Component({
  selector: 'app-check-user-profile',
  templateUrl: './check-user-profile.component.html',
  styleUrl: './check-user-profile.component.css'
})
export class CheckUserProfileComponent {
  question: any;
  user: any;
  username: any;
  constructor( 
     private router: Router,
     private service: CheckUserProfileService,
     private route: ActivatedRoute) {

      this.route.paramMap.subscribe(params => {
        this.username = params.get('id');
      });

      this.readUserProfile();
      
  }


  readUserProfile() {
    this.service.getUserProfile(this.username).subscribe((data) => {
      this.user = data;
    })
  }
}
