import { Component, OnInit } from '@angular/core';
import { NgxGalleryOptions, NgxGalleryImage, NgxGalleryAnimation } from 'ngx-gallery-9';
import {GalleryService} from 'src/app/landing/service/gallery.service';
import {ActivatedRoute} from '@angular/router';
import {DomSanitizer} from '@angular/platform-browser';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-base64gallery',
  templateUrl: './base64gallery.component.html',
  styleUrls: ['./base64gallery.component.css']
})
export class Base64galleryComponent implements OnInit {

  constructor(private postData: GalleryService, private route: ActivatedRoute, private _sanitizer: DomSanitizer) { }
  houseID: any;
  data: any;
  private routeSub: Subscription;
  galleryOptions: NgxGalleryOptions[];
  galleryImages: NgxGalleryImage[];

  ngOnInit(): void {




    this.galleryOptions = [
      {
        width: '50vw',
        height: '35vw',
        thumbnailsColumns: 4,
        imageAnimation: NgxGalleryAnimation.Slide
      },
      {
        breakpoint: 1000,
        width: '70vw',
        height: '60vw',
        imagePercent: 80,
        thumbnailsPercent: 20,
        thumbnailsMargin: 20,
        thumbnailMargin: 20
      },

      {
        breakpoint: 800,
        width: '70vw',
        height: '80vw',
        imagePercent: 80,
        thumbnailsPercent: 20,
        thumbnailsMargin: 5,
        thumbnailMargin: 5
      },

      {
        breakpoint: 400,
        preview: false
      }
    ];
    this.routeSub = this.route.params.subscribe(params => {
      this.houseID = params.id;

    });
    this.galleryImages = [];

    this.postData.getPosts(this.houseID).subscribe((result) => {

      this.data = result;

      const photos = this.data.photos;
      for (let i = 0; i < 5; i++){
        let imagePath=null
        if(photos[i]!==undefined)
         imagePath = 'data:image/jpg;base64,' + photos[i];
        else  imagePath="/assets/house.png";


        const img = 'data:image/jpg;base64,' + imagePath;

        this.galleryImages.push({ small : imagePath, medium : imagePath, big : imagePath});

      }
    });






  }
}
