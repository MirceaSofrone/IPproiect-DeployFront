import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PhotoCarouselComponent } from './photo-carousel/photo-carousel.component';

import { NgImageSliderModule } from 'ng-image-slider';
import { Carousel2Component } from './carousel2/carousel2.component';
import { DragScrollModule } from 'ngx-drag-scroll';
import { Carousel3Component } from './carousel3/carousel3.component';

import {IvyCarouselModule} from 'angular-responsive-carousel';
import {SwiperModule} from 'swiper/angular';

@NgModule({
  declarations: [
    AppComponent,
    PhotoCarouselComponent,
    Carousel2Component,
    Carousel3Component
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NgImageSliderModule,
    DragScrollModule,
    IvyCarouselModule,
    SwiperModule
  ],
  providers: [],
  bootstrap: [AppComponent],
   

})
export class AppModule { }
