import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BuyProgramService } from './buy-program.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { JwtTokenService } from '../jwt-token/jwt-token.service';

@Component({
  selector: 'app-buy-program',
  templateUrl: './buy-program.component.html',
  styleUrl: './buy-program.component.css'
})
export class BuyProgramComponent {
  data: any = [];
  show: any = false;
  selectedItem: any = null;
  message : any = null;
  user: any;
  public firstForm : FormGroup;

  constructor( 
    private formBuilder: FormBuilder,
     private router: Router,
     private service: BuyProgramService,
     private jwtService: JwtTokenService) {
      
      this.jwtService.getUserById().subscribe((data: any) => {
        this.user = data;
     });

      this.readData();
      

      this.firstForm = formBuilder.group({
        paymentMethod : [null],
        paymentValue : [null]
      });

  }

  readData() {
    
    this.service.baseUrl += this.user.id;
      this.service.getUnparticipated().subscribe((data) => {
        this.data = data;
        //console.log(data);
      },
      error => console.log(error));

    }

    showPart(item: any) {
      this.selectedItem = this.selectedItem === item ? null : item;
      event?.preventDefault();
    }

    buyProgram() {

      if(this.selectedItem) {
        const obj = {
          programId: this.selectedItem.id,
          korisnikId: this.user.id,
          nacinPlacanja: this.firstForm.get('paymentMethod')?.value,
          vrijednost: parseInt(this.firstForm.get('paymentValue')?.value, 10),
        }

        //console.log(JSON.stringify(obj));

        this.service.subscribeToProgram(obj).subscribe((data) => {
          //console.log(data);
          this.message = null;
        }, (error:any) => {
          this.message = "Not enough money";
        })
        
      }
      event?.preventDefault();
      
    }
}
