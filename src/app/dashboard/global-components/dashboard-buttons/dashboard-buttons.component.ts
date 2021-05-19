import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard-buttons',
  templateUrl: './dashboard-buttons.component.html',
  styleUrls: ['./dashboard-buttons.component.css']
})
export class DashboardButtonsComponent {
  isSellerBtnClicked = false;
  isUserBtnClicked = false;
  isAddPropertyBtnClicked = false;
  highlightSeller():void{
    
   let sellerBtn = (<HTMLInputElement>document.getElementById("seller--btn"));
    let userBtn = (<HTMLInputElement>document.getElementById("user--btn"));
    let addPropertyBtn = (<HTMLInputElement>document.getElementById("add__property--btn"));
    sellerBtn.classList.add('active__state');
    if(userBtn.classList.contains('active__state') || addPropertyBtn.classList.contains('active__state')){
     userBtn.classList.remove('active__state');
     addPropertyBtn.classList.remove('active__state');
  }
}
  highlightAddProperty():void{
    let addPropertyBtn = (<HTMLInputElement>document.getElementById("add__property--btn"));
     let sellerBtn = (<HTMLInputElement>document.getElementById("seller--btn"));
    let userBtn = (<HTMLInputElement>document.getElementById("user--btn"));
    addPropertyBtn.classList.add('active__state');
    if(sellerBtn.classList.contains('active__state') || userBtn.classList.contains('active__state')){
      sellerBtn.classList.remove('active__state');
      userBtn.classList.remove('active__state');
    }
  }
}
