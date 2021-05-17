import { Component, OnInit } from '@angular/core';
import {NgxGalleryOptions} from '@kolkov/ngx-gallery';
import {NgxGalleryImage} from '@kolkov/ngx-gallery';
import {NgxGalleryAnimation} from '@kolkov/ngx-gallery';

import { GalleryService } from '../galleryService/gallery.service';

@Component({
  selector: 'app-gallery-part',
  templateUrl: './gallery-part.component.html',
  styleUrls: ['./gallery-part.component.css']
})

export class GalleryPartComponent implements OnInit {

  galleryOptions: NgxGalleryOptions[];
  galleryImages: NgxGalleryImage[];

  data:any;
  constructor(private postData:GalleryService) { }


  ngOnInit(): void {


    this.galleryImages = [];

    this.postData.getPosts().subscribe((result)=>{
      //console.warn("result", result);
      this.data=result;
      for(let i=0; i<5; i++){
        console.warn("result", result[i]);
        this.galleryImages.push({ "small" : result[i]["download_url"], "medium" : result[i]["download_url"], "big" : result[i]["download_url"]})
      }
  })


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
  
  }
}
