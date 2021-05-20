import { Component, OnInit } from '@angular/core';
import {Subscription} from 'rxjs';
import {ActivatedRoute} from '@angular/router';
import {HttpClient, HttpParams} from '@angular/common/http';

@Component({
  selector: 'app-house-info',
  templateUrl: './house-info.component.html',
  styleUrls: ['./house-info.component.css']
})
export class HouseInfoComponent implements OnInit {
  private routeSub: Subscription;
  houseID: any;
  result: any;
  constructor( private route: ActivatedRoute, private http: HttpClient) { }

  ngOnInit(): void {
    this.routeSub = this.route.params.subscribe(params => {
      this.houseID = params.id;
      console.log(this.houseID, 'house id');
    });
    const url = 'https://back-end-hpp.herokuapp.com/api/v1/housedetails';
    const params = new HttpParams()
      .set('houseID', this.houseID);
    this.http.get(url, {params})
      .subscribe((result: any) => {
        this.result = result;
        console.warn('result', this.result);
        localStorage.setItem('sellerID', result.userID);
        localStorage.setItem('houseID', result.houseID);
      });
    console.log(this.result);
  }

  // infoList:{price:string,mediumPrice:string,location:string,rooms:string,floor:string,surface:string,bathrooms:string,type:string,description:string}[]=info;
}
