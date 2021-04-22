import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';



import SearchIcon from '@material-ui/icons/Search';
import { AppComponent } from './app.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LandingSearchComponent } from './landing-search/landing-search.component';
import {FormsModule} from '@angular/forms';
import{ReactiveFormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatIcon, MatIconModule} from '@angular/material/icon';

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


import {FormsModule} from '@angular/forms';
import{ReactiveFormsModule} from '@angular/forms';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CarduriComponent } from './carduri-part/carduri.component';

@NgModule({
  declarations: [
    AppComponent,

    CarduriComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule


    LandingSearchComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    MatInputModule,
    MatIconModule


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
