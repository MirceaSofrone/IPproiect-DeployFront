import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-seller-house-panel',
  templateUrl: './seller-house-panel.component.html',
  styleUrls: ['./seller-house-panel.component.css']
})
export class SellerHousePanelComponent implements OnInit {
  @Input() house: any;
 
  ngOnInit(): void {
    this.house = JSON.parse(JSON.stringify(this.house));
  }

  getPrice(price: number): string {
    let stringPrice = '$';
    let counter = 0;
    while (price >= 1) {
      counter++;
      stringPrice = price % 10 + stringPrice;
      price = price / 10;
      if (counter === 3 && price >= 1) {
        counter = 0;
        stringPrice = '.' + stringPrice;
      }
    }
    return stringPrice;
  }

}
