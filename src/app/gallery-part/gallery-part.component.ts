import { Component, OnInit } from '@angular/core';
import {NgxGalleryOptions} from '@kolkov/ngx-gallery';
import {NgxGalleryImage} from '@kolkov/ngx-gallery';
import {NgxGalleryAnimation} from '@kolkov/ngx-gallery';

import { GalleryService } from '../galleryService/gallery.service';
import {ActivatedRoute} from '@angular/router';
import {Subscription} from 'rxjs';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-gallery-part',
  templateUrl: './gallery-part.component.html',
  styleUrls: ['./gallery-part.component.css']
})

export class GalleryPartComponent implements OnInit {

  galleryOptions: NgxGalleryOptions[];
  galleryImages: NgxGalleryImage[];
houseID: any;
  data: any;
  private routeSub: Subscription;

  constructor(private postData: GalleryService, private route: ActivatedRoute, private _sanitizer: DomSanitizer) { }


  ngOnInit(): void {

    this.routeSub = this.route.params.subscribe(params => {
      this.houseID = params.id;
      console.log(this.houseID, 'house id');
    });
    this.galleryImages = [];

    this.postData.getPosts(this.houseID).subscribe((result) => {
      // console.warn("result", result);
      this.data = result;
      console.log(this.data, 'galerie');
      const photos = this.data.photos;
      for (let i = 0; i < 5; i++){
        const imagePath = this._sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,'
          + photos[i]);
        console.log(imagePath, 'image');
        // const image=imagePath.get("changingThisBreaksApplicationSecurity")
        this.galleryImages.push({ small : imagePath, medium : imagePath, big : imagePath});
      }
  });


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
