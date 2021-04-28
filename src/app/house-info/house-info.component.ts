import { Component, OnInit } from '@angular/core';

import info from './_files/info.json';

@Component({
  selector: 'app-house-info',
  templateUrl: './house-info.component.html',
  styleUrls: ['./house-info.component.css']
})
export class HouseInfoComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  infoList:{price:string,mediumPrice:string,location:string,rooms:string,floor:string,surface:string,bathrooms:string,type:string,description:string}[]=info;
}
