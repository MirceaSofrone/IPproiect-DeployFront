import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-locuinte-btn',
  templateUrl: './locuinte-btn.component.html',
  styleUrls: ['./locuinte-btn.component.css']
})
export class LocuinteBtnComponent implements OnInit {
name: string = 'Estates';
  constructor() { }

  ngOnInit(): void {
  }

}
