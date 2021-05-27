import {Component, OnInit, SimpleChanges} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';


@Component({
  selector: 'app-house-details',
  templateUrl: './house-details.component.html',
  styleUrls: ['./house-details.component.css']
})
export class HouseDetailsComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute) {
    // Code snippet
    this.subscribeRouteChange();
  }
  ngOnInit(): void {

  }
  ngonChanges(changes: SimpleChanges){

  }
  subscribeRouteChange() {
    this.activatedRoute.params.subscribe((params = {}) => {

    });
  }
}
