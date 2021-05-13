import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent implements OnInit {

 
  constructor(private _location: Location) { }

  ngOnInit(): void {
  }
  
  goBack() {
    this._location.back();
  }

}
