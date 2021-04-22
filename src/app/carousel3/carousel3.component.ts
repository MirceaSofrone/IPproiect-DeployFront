import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-carousel3',
  templateUrl: './carousel3.component.html',
  styleUrls: ['./carousel3.component.css']
})
export class Carousel3Component implements OnInit {

  constructor() { }
  images = [{
    image: './assets/house.png',
    thumbImage: './assets/house.png',
    alt: 'alt of image',
    title: '70.000$ 5 camere Valea Lupului, Iasi'
}, {
    image: './assets/house3.png',
    thumbImage: './assets/house3.png',
    title: '70.000$ 5 camere Valea Lupului, Iasi'
}, {
    image: './assets/house2.png',
    thumbImage: './assets/house2.png',
    title: '70.000$ 5 camere Valea Lupului, Iasi'
},{
    image: './assets/house.png',
    thumbImage: './assets/house.png',
    title: '70.000$ 5 camere Valea Lupului, Iasi'
}, {
    image: './assets/house2.png',
    thumbImage: './assets/house2.png',
    title: '70.000$ 5 camere Valea Lupului, Iasi'
}, {
    image: './assets/house3.png',
    thumbImage: './assets/house3.png',
    title: '70.000$ 5 camere Valea Lupului, Iasi'
}];
  ngOnInit(): void {
  }

}
