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
  infoList:{price:String,mediumPrice:String,location:String,rooms:String,floor:String,surface:String,bathrooms:String,type:String,description:String}[]=info;
}
