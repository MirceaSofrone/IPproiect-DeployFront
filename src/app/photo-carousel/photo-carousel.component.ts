import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-photo-carousel',
  templateUrl: './photo-carousel.component.html',
  styleUrls: ['./photo-carousel.component.css']
})
export class PhotoCarouselComponent implements OnInit {

  constructor() { }

  name = 'Angular';
  imageObject = [{
      image: './assets/house.png',
      thumbImage: './assets/house.png',
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
