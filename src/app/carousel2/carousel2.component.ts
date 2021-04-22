import { Component, OnInit, ViewChild } from '@angular/core';
import { DragScrollComponent } from 'ngx-drag-scroll';

@Component({
  selector: 'app-carousel2',
  templateUrl: './carousel2.component.html',
  styleUrls: ['./carousel2.component.css']
})
export class Carousel2Component implements OnInit {

  // constructor() { }

  @ViewChild('nav', {read: DragScrollComponent}) ds: DragScrollComponent;
  
  moveLeft() {
    this.ds.moveLeft();
  }

  moveRight() {
    this.ds.moveRight();
  }

  moveTo(index) {
    this.ds.moveTo(index);
  }

  ngAfterViewInit() {
    // Starting ngx-drag-scroll from specified index(3)
    setTimeout(() => {
      this.ds.moveTo(3);
    }, 0);
  }
  ngOnInit(): void {
  }

}
// class Sample{
//   @ViewChild('nav', {read: DragScrollComponent}) ds: DragScrollComponent;
  
//   moveLeft() {
//     this.ds.moveLeft();
//   }

//   moveRight() {
//     this.ds.moveRight();
//   }

//   moveTo(index) {
//     this.ds.moveTo(index);
//   }

//   ngAfterViewInit() {
//     // Starting ngx-drag-scroll from specified index(3)
//     setTimeout(() => {
//       this.ds.moveTo(3);
//     }, 0);
//   }
// }