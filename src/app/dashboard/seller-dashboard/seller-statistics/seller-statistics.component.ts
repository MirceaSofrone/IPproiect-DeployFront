import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IHouse } from "../seller-panel/house";

@Component({
  selector: 'app-seller-statistics',
  templateUrl: './seller-statistics.component.html',
  styleUrls: ['./seller-statistics.component.css']
})
export class SellerStatisticsComponent {
  private URL = 'https://back-end-hpp.herokuapp.com/api/v1/all/';
  private housesList: any[];
  private rawFavoriteData: any[] = [];
  private rawViewsData: any[] = [];
  private data: any[] = [{
    name: 'Average Houses Followers',
    series: []
  },
  {
    name: 'Average Houses Visitors',
    series: []
  }];

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    const favoriteData: any[] = [];
    const viewsData: any[] = [];
    const headers = {'Authorization': 'Bearer ' + localStorage.getItem('token') };
    this.http.get<IHouse[]>(this.URL + localStorage.getItem('userID'), { headers }).toPromise().then((data) => {
      this.housesList = JSON.parse(JSON.stringify(data));

      this.housesList.forEach(house => {
        // get house favorite history
        for (const favorite in house.favoriteHistory) {
          if (house.favoriteHistory.hasOwnProperty(favorite)) {
            this.rawFavoriteData.push({time: this.timestampToUnix(favorite), favorites: house.favoriteHistory[favorite]});
          }
        }

        // get house views history
        for (const views in house.viewsHistory) {
          if (house.viewsHistory.hasOwnProperty(views)) {
            this.rawViewsData.push({time: this.timestampToUnix(views), views: house.viewsHistory[views]});
          }
        }
      });

      // Creates data for the graph
      if (this.rawFavoriteData !== undefined && this.rawFavoriteData.length !== 0) {
        this.rawFavoriteData.sort((first, second) => (first.time - second.time)).forEach(follower => {
          favoriteData.push({value: follower.favorites, name: this.unixToDate(follower.time)});
        });
        this.rawViewsData.sort((first, second) => (first.time - second.time)).forEach(follower => {
          viewsData.push({value: follower.views, name: this.unixToDate(follower.time)});
        });
        this.data[0].series = favoriteData;
        this.data[1].series = viewsData;
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
