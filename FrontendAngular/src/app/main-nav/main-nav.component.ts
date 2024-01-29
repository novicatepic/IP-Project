import { Component, OnInit, inject } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { JwtTokenService } from '../jwt-token/jwt-token.service';
import { Router } from '@angular/router';
import { AuthServiceService } from '../auth-service/auth-service.service';
import { environment } from '../../environments/environment';

@Component({
  selector: 'main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.css']
})
export class MainNavComponent implements OnInit {
  private breakpointObserver = inject(BreakpointObserver);

  user: any;
  loggedIn: boolean = false;

  private key = environment.storageKey;

  constructor(private jwtService: JwtTokenService, private router : Router, private authService2 : AuthServiceService) {

    //user logged in
    if(this.jwtService.getUserById()) {
      this.jwtService.getUserById().subscribe((data: any) => {
        this.loggedIn = true;
        this.user = data;
     }, (err: any) => console.log("ERR" + err));
    } //else 
    else {
      this.loggedIn = false;
    }

  }

  ngOnInit() {
    // Subscribe to the loginSuccess$ observable
    this.authService2.loginSuccess$.subscribe(() => {
      // Update the MainNavComponent when a successful login occurs
      this.loggedIn = true;
    });
  }

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

    logout() {
      localStorage.removeItem(this.key);
      this.loggedIn = false;
      this.router.navigate(['/']);
    }

}
