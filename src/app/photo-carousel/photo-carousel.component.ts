import { Component, OnInit, ViewChild, ChangeDetectorRef} from '@angular/core';
import { PhotoCarouselService } from '../service/photo-carousel.service';
import{HttpClient} from '@angular/common/http';

// import SwiperCore from 'swiper/core';
import {SwiperModule} from 'swiper/angular';

import { Variable } from '@angular/compiler/src/render3/r3_ast';
// import Swiper, {SwiperOptions} from "swiper";
import {Swiper, SwiperOptions} from "swiper";
import { SwiperComponent } from "swiper/angular";

import SwiperCore, {
    Navigation,
    Pagination,
    Scrollbar,
    A11y,
    Virtual,
    Zoom,
    Autoplay,
    Thumbs,
    Controller
  } from "swiper/core";
  
  // install Swiper components
  SwiperCore.use([
    Navigation,
    Pagination,
    Scrollbar,
    A11y,
    Virtual,
    Zoom,
    Autoplay,
    Thumbs,
    Controller
  ]);

@Component({
  selector: 'app-photo-carousel',
  templateUrl: './photo-carousel.component.html',
  styleUrls: ['./photo-carousel.component.css']
})
export class PhotoCarouselComponent implements OnInit {

//   constructor(private photoCarousel:PhotoCarouselService) { }
data:any;
constructor(private postData:PhotoCarouselService, private cd: ChangeDetectorRef) {}
  onSwiper(swiper) {
    console.log(swiper);
  }
  onSlideChange() {
    console.log('slide change');
  }
 


ngOnInit() {
  this.postData.getPosts().subscribe((result)=>{
    console.warn("result", result);
    this.data=result;
})
    // this.data.forEach(m => console.log(m.id))
 
}
@ViewChild("swiperRef", { static: false }) swiperRef?: SwiperComponent;

  show: boolean;
  thumbs: any;
  //constructor(private cd: ChangeDetectorRef) {}
  

  thumbsSwiper: any;
  setThumbsSwiper(swiper) {
    this.thumbsSwiper = swiper;
  }
  controlledSwiper: any;
  setControlledSwiper(swiper) {
    this.controlledSwiper = swiper;
  }

  indexNumber = 1;
  exampleConfig = { slidesPerView: 3 };
  slidesPerView: number = 4;
  pagination: any = false;

  slides2 = ["slide 1", "slide 2", "slide 3"];
  replaceSlides() {
    this.slides2 = ["foo", "bar"];
  }

  togglePagination() {
    if (!this.pagination) {
      this.pagination = { type: "fraction" };
    } else {
      this.pagination = false;
    }
  }

  navigation = false;
  toggleNavigation() {
    this.navigation = !this.navigation;
  }

  scrollbar: any = false;
  toggleScrollbar() {
    if (!this.scrollbar) {
      this.scrollbar = { draggable: true };
    } else {
      this.scrollbar = false;
    }
  }
  breakpoints = {
    640: { slidesPerView: 2, spaceBetween: 20 },
    780: { slidesPerView: 3, spaceBetween: 40 },
    1024: { slidesPerView: 3, spaceBetween: 50 }
  };

  slides = Array.from({ length: 5 }).map((el, index) => `Slide ${index + 1}`);
  virtualSlides = Array.from({ length: 600 }).map(
    (el, index) => `Slide ${index + 1}`
  );

  log(string) {
    // console.log(string);
  }

  breakPointsToggle: boolean;
  breakpointChange() {
    this.breakPointsToggle = !this.breakPointsToggle;
    this.breakpoints = {
      640: { slidesPerView: 2, spaceBetween: 20 },
      780: { slidesPerView: 3, spaceBetween: 40 },
      1024: { slidesPerView: this.breakPointsToggle ? 7 : 5, spaceBetween: 50 }
    };
  }


  name = 'Angular';
  imageObject = [{
      image: 'this.data.url',
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
  },      
  {image: './assets/house.png',
  thumbImage: './assets/house.png',
  alt: 'alt of image',
  title: '70.000$ 5 camere Valea Lupului, Iasi'
}, {
  image: 'this.data.url',
  thumbImage: './assets/house3.png',
  title: '70.000$ 5 camere Valea Lupului, Iasi'
}, {
  image: './assets/house2.png',
  thumbImage: './assets/house2.png',
  title: '70.000$ 5 camere Valea Lupului, Iasi'
}];


}
