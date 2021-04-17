import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HouseGalleryComponent } from './house-gallery/house-gallery.component';
import { UserContactComponent } from './user-contact/user-contact.component';

@NgModule({
  declarations: [
    AppComponent,
    HouseGalleryComponent,
    UserContactComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
