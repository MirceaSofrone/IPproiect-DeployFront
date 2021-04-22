import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-carduri',
  templateUrl: './carduri.component.html',
  styleUrls: ['./carduri.component.css']
})
export class CarduriComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    console.log('aici');
    const user = document.getElementById('search-form');
    console.log(user);
  }

}