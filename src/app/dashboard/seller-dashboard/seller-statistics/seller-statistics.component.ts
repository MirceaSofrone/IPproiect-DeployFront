import {Component, Input} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IHouse } from "../seller-panel/house";

@Component({
  selector: 'app-seller-statistics',
  templateUrl: './seller-statistics.component.html',
  styleUrls: ['./seller-statistics.component.css']
})
export class SellerStatisticsComponent {
  @Input() SELLER_ID;
  private URL = 'https://house-prediction-fii.herokuapp.com/api/v1/all/';
  housesList: any[];
  rawData: any[] = [];
  data: any[] = [{
    name: 'Average Houses Followers',
    series: []
  }];

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    const followersData: any[] = [];
    this.http.get<IHouse[]>(this.URL + this.SELLER_ID).toPromise().then((data) => {
      this.housesList = JSON.parse(JSON.stringify(data));

      this.housesList.forEach(house => {
        for (const favorite in house.favoriteHistory) {
          if (house.favoriteHistory.hasOwnProperty(favorite)) {
            let hasDate = false;

            // Appends to an existent entry
            this.rawData.forEach(raw => {
              if (this.unixToDate(raw.time) === this.unixToDate(this.timestampToUnix(favorite))) {
                raw.favorites = raw.favorites + house.favoriteHistory[favorite];
                hasDate = true;
              }
            });

            // Adds new entry
            if (!hasDate) {
              this.rawData.push({time: this.timestampToUnix(favorite), favorites: house.favoriteHistory[favorite]});
            }
          }
        }
      });

      // Creates data for the graph
      if (this.rawData !== undefined && this.rawData.length !== 0) {
        this.rawData.sort((first, second) => (first.time - second.time)).forEach(follower => {
          followersData.push({value: follower.favorites, name: this.unixToDate(follower.time)});
        });
        this.data[0].series = followersData;
      }
    });
  }

  getData(): any[] {
    return this.data;
  }

  isData(): boolean {
    return (this.data[0].series !== undefined && this.data[0].series.length !== 0);
  }

  timestampToUnix(timestamp: string): number {
    return Math.trunc(Date.parse(timestamp) / 1000);
  }

  unixToDate(unixtime: number): string {
    const date = new Date(unixtime * 1000);
    const day = ('0' + date.getDay()).substr(-2);
    const month = ('0' + date.getMonth()).substr(-2);
    const year = date.getFullYear();

    return day + '.' + month + '.' + year;
  }
}
