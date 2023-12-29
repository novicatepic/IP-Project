import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ShowProfileService } from './show-profile.service';
import { JwtTokenService } from '../jwt-token/jwt-token.service';

@Component({
  selector: 'app-show-profile',
  templateUrl: './show-profile.component.html',
  styleUrl: './show-profile.component.css'
})
export class ShowProfileComponent {
  question: any;
  user: any;
  constructor( 
     private router: Router,
     private service: ShowProfileService,
     private jwtService: JwtTokenService) {

      this.jwtService.getUserById().subscribe((data: any) => {
         this.user = data;
      });
      
      
  }

  showProfileChange() {
    this.router.navigate(['/change-profile']);
  }

}

