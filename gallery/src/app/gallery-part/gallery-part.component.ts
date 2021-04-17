import { Component, OnInit } from '@angular/core';
import {NgxGalleryOptions} from '@kolkov/ngx-gallery';
import {NgxGalleryImage} from '@kolkov/ngx-gallery';
import {NgxGalleryAnimation} from '@kolkov/ngx-gallery';
@Component({
  selector: 'app-gallery-part',
  templateUrl: './gallery-part.component.html',
  styleUrls: ['./gallery-part.component.css']
})

export class GalleryPartComponent implements OnInit {
 
  galleryOptions: NgxGalleryOptions[];
  galleryImages: NgxGalleryImage[];

  constructor() { }

  ngOnInit(): void {
    this.galleryOptions = [
    {
      width: '600px',
      height: '400px',
      thumbnailsColumns: 4,
      imageAnimation: NgxGalleryAnimation.Slide
    },
    // max-width 800
    {
      breakpoint: 800,
      width: '100%',
      height: '600px',
      imagePercent: 80,
      thumbnailsPercent: 20,
      thumbnailsMargin: 20,
      thumbnailMargin: 20
    },
    // max-width 400
    {
      breakpoint: 400,
      preview: false
    }
  ];

  this.galleryImages = [
    {
      small: "../../assets/1.jpg",
      medium: "../../assets/1.jpg",
      big: "../../assets/1.jpg"
    },
    {
      small: "../../assets/2.jpg",
      medium: "../../assets/2.jpg",
      big: "../../assets/2.jpg"
    },
    {
      small: "../../assets/3.jpg",
      medium: "../../assets/3.jpg",
      big: "../../assets/3.jpg"
    },{
      small: "../../assets/4.jpg",
      medium: "../../assets/4.jpg",
      big: "../../assets/4.jpg"
    },
    {
      small: "../../assets/5.jpg",
      medium: "../../assets/5.jpg",
      big: "../../assets/5.jpg"
    }
  ];
  }
}
