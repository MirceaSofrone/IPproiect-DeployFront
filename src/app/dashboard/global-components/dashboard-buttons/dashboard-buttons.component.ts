import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard-buttons',
  templateUrl: './dashboard-buttons.component.html',
  styleUrls: ['./dashboard-buttons.component.css']
})
export class DashboardButtonsComponent implements OnInit {
  @Input() selectedButton;
  isSellerBtnClicked = false;
  isUserBtnClicked = false;
  isAddPropertyBtnClicked = false;

  constructor() {
  }

  ngOnInit(): void {
    if (this.selectedButton === 'user') {
      this.highlightUser();
    } else if (this.selectedButton === 'seller') {
      this.highlightSeller();
    } else if (this.selectedButton === 'add') {
      this.highlightAddProperty();
    }
  }

  highlightUser(): void {
    this.isUserBtnClicked = true;
    this.isSellerBtnClicked = false;
    this.isAddPropertyBtnClicked = false;
  }

  highlightSeller(): void {
    this.isUserBtnClicked = false;
    this.isSellerBtnClicked = true;
    this.isAddPropertyBtnClicked = false;
  }

  highlightAddProperty():void{
    this.isUserBtnClicked = false;
    this.isSellerBtnClicked = false;
    this.isAddPropertyBtnClicked = true;
  }
}
