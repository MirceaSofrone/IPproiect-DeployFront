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

  statData: any[] = [];

  data: any[] = [{
    name: 'First House',
    series: []
  },{
    name: 'Second House',
    series: []
  },{
    name: 'Third House',
    series: []
  }];

  keyArr_0: any[] = [];
  keyArr_1: any[] = [];
  keyArr_2: any[] = [];
  ok = 0;

  constructor(private http: HttpClient) {
    const headers = { 'Authorization': 'Bearer ' + localStorage.getItem('token') };
    this.http.get<IUserStatistics[]>(this.URL + localStorage.getItem('userID'), { headers }).subscribe(
      // reqData => this.statData = reqData,
      // () => {},
      { 
        next:(result) =>{ this.statData = result},
        error:(err:any) => {console.log(err);},
        complete:()=> {this.data = this.getData();
        this.ok = 1;}
      }
      );
      //this.data = this.getData();
  }

  getReqData(): any[] {
    if(this.ok == 1){
      return this.data;
    }
  }

  getData(): any[] {
      let aux_0: any[] = [];
      let aux_1: any[] = [];
      let aux_2: any[] = [];

      if(this.statData.length > 0){
      this.keyArr_0 = Object.keys(this.statData[0].priceHistory);
      for(let i = 0; i < this.keyArr_0.length; i++){
        aux_0.push(
          { 
            value: this.statData[0].priceHistory[this.keyArr_0[i]],
            name: this.transformDate(this.keyArr_0[i])
          });
      }
      this.data[0].series = aux_0;
    }

      if(this.statData[1] != undefined){
          this.keyArr_1 = Object.keys(this.statData[1].priceHistory);
          for(let i = 0; i < this.keyArr_1.length; i++){
            aux_1.push(
              { 
                value: this.statData[1].priceHistory[this.keyArr_1[i]],
                name: this.transformDate(this.keyArr_1[i])
              });
          }
          this.data[1].series = aux_1;
      }

      if(this.statData[2] != undefined){
        this.keyArr_2 = Object.keys(this.statData[2].priceHistory);
        for(let i = 0; i < this.keyArr_2.length; i++){
          aux_2.push(
            { 
              value: this.statData[2].priceHistory[this.keyArr_2[i]],
              name: this.transformDate(this.keyArr_2[i])
            });
        }
        this.data[2].series = aux_2;
      }
      return this.data;
  }

  transformDate(date) {
    return formatDate(date, 'mediumDate', 'en-US');
  }
}
