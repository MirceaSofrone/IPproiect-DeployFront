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
      width: '45vw',
      height: '40vw',
      thumbnailsColumns: 4,
      imageAnimation: NgxGalleryAnimation.Slide
    },
    {
      breakpoint: 1110,
      width: '100%',
      height: '60vw',
      imagePercent: 80,
      thumbnailsPercent: 20,
      thumbnailsMargin: 20,
      thumbnailMargin: 20
    },
    // max-width 800
    {
      breakpoint: 800,
      width: '100%',
      height: '80vw',
      imagePercent: 80,
      thumbnailsPercent: 20,
      thumbnailsMargin: 5,
      thumbnailMargin: 5
    },
    // max-width 400
    {
      breakpoint: 400,
      preview: false
    }
  ];
  

    this.galleryImages = [
    {
<<<<<<< HEAD:gallery/src/app/gallery-part/gallery-part.component.ts
      small: "https://picsum.photos/id/0/5616/3744",
      medium: "https://picsum.photos/id/0/5616/3744",
      big: "https://picsum.photos/id/0/5616/3744"
=======
      small: '../../assets/1.jpg',
      medium: '../../assets/1.jpg',
      big: '../../assets/1.jpg'
>>>>>>> 6171a5cb394f450b1f6108e6f03325372461a919:src/app/gallery-part/gallery-part.component.ts
    },
    {
      small: '../../assets/2.jpg',
      medium: '../../assets/2.jpg',
      big: '../../assets/2.jpg'
    },
    {
      small: '../../assets/3.jpg',
      medium: '../../assets/3.jpg',
      big: '../../assets/3.jpg'
    }, {
      small: '../../assets/4.jpg',
      medium: '../../assets/4.jpg',
      big: '../../assets/4.jpg'
    },
    {
      small: '../../assets/5.jpg',
      medium: '../../assets/5.jpg',
      big: '../../assets/5.jpg'
    }
  ];
  }
}
