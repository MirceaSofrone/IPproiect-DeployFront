import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {IHouse} from './house';


@Component({
  selector: 'app-seller-panel',
  templateUrl: './seller-panel.component.html',
  styleUrls: ['./seller-panel.component.css']
})
export class SellerPanelComponent {

  private housesList: any[];
  private URL = '/assets/data/houses.json';

  constructor(private http: HttpClient) {
    this.http.get<IHouse[]>(this.URL).subscribe(data => this.housesList = data);
  }

  getHousesList = () => this.housesList;
}
