import { Component, Input, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-seller-panel',
  templateUrl: './seller-panel.component.html',
  styleUrls: ['./seller-panel.component.css']
})
export class SellerPanelComponent implements OnInit{
  private housesList = [{}, {}, {}];
  @Input() SELLER_ID;
  private URL = 'https://back-end-hpp.herokuapp.com/api/v1/all/';

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    const headers = {'Authorization': 'Bearer ' + localStorage.getItem('token')};
    this.http.get(this.URL + localStorage.getItem('userID'), {headers}).toPromise().then((data) => {
      this.housesList = JSON.parse(JSON.stringify(data));
    });
  }

  getHousesList = () => this.housesList;
}
