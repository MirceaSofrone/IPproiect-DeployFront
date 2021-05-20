import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-seller-panel',
  templateUrl: './seller-panel.component.html',
  styleUrls: ['./seller-panel.component.css']
})
export class SellerPanelComponent {
  private housesList: any[];
  // TODO: Get seller id as input
  private SELLER_ID = '6757fff1-e437-4d23-bd45-646a4b419b16';
  private URL = 'https://house-prediction-fii.herokuapp.com/api/v1/all/';

  constructor(private http: HttpClient) {
    this.http.get(this.URL + this.SELLER_ID).toPromise().then((data) => {
      this.housesList = JSON.parse(JSON.stringify(data));
    });
  }

  getHousesList = () => this.housesList;
}
