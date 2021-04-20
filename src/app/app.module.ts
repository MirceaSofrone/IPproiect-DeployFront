import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PhotoCarouselComponent } from './photo-carousel/photo-carousel.component';

import { NgImageSliderModule } from 'ng-image-slider';


@NgModule({
  declarations: [
    AppComponent,
    PhotoCarouselComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    NgImageSliderModule
  ],
  providers: [],
  bootstrap: [AppComponent],
   

})
export class AppModule { }
