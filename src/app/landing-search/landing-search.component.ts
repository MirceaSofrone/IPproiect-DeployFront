import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-landing-search',
  templateUrl: './landing-search.component.html',
  styleUrls: ['./landing-search.component.css']
})
export class LandingSearchComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    console.log('aici');
    const user = document.getElementById('search-form');
    console.log(user);
  }

}
