import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-adauga-proprietate-btn',
  templateUrl: './adauga-proprietate-btn.component.html',
  styleUrls: ['./adauga-proprietate-btn.component.css']
})
export class AdaugaProprietateBtnComponent implements OnInit {
name: string = 'Add Estate';
  constructor() { }

  ngOnInit(): void {
  }

}
