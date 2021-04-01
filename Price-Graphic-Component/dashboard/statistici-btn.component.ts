import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-statistici-btn',
  templateUrl: './statistici-btn.component.html',
  styleUrls: ['./statistici-btn.component.css']
})
export class StatisticiBtnComponent implements OnInit {
name: string = 'Statistics';
  constructor() { }

  ngOnInit(): void {
  }

}
