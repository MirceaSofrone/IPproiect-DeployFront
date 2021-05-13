import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';
@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css']
})
export class EditorComponent implements OnInit {

  constructor(private _location: Location) { }

  ngOnInit(): void {
  }

  goBack() {
    this._location.back();
  }

}
