import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IUserStatistics } from './userStatistics';
import { formatDate  } from '@angular/common'

@Component({
  selector: 'app-user-statistics-panel',
  templateUrl: './user-statistics-panel.component.html',
  styleUrls: ['./user-statistics-panel.component.css']
})

export class UserStatisticsPanelComponent {
  URL = 'https://back-end-hpp.herokuapp.com/api/v1/users/getfavorite/';
  once = false;

  statData: any[] = [];

  data: any[] = [{
    name: 'Price',
    series: []
  }];
  keyArr: any[];

  constructor(private http: HttpClient) {
    const headers = { 'Authorization': 'Bearer ' + localStorage.getItem('token') };
    this.http.get<IUserStatistics[]>(this.URL + localStorage.getItem('userID'), { headers }).subscribe(
      data => this.statData = data,
      () => {},
    );
  }

  getData(): any[] {
    let followersData: any[] = [];
    this.keyArr = Object.keys(this.statData[0].priceHistory);
    this.statData.forEach(follower => {
      followersData.push(
        { value: this.statData[0].priceHistory[this.keyArr[0]],
          name: this.transformDate(this.keyArr[0])
        }
      );
    });
    this.data[0].series = followersData;
    return this.data;
  }

  transformDate(date) {
    return formatDate(date, 'mediumDate', 'en-US');
  }

}
