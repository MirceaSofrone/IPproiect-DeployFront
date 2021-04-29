import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { FormsModule }   from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GalleryPartComponent } from './gallery-part/gallery-part.component';
import {UserContactComponent} from './user-contact/user-contact.component';
import {HouseInfoComponent} from './house-info/house-info.component';
import {MapComponent} from './map/map.component';
import { PhotoCarouselComponent } from './photo-carousel/photo-carousel.component';
import {NavbarComponent} from './navbar/navbar.component';
import {NavbarLoggedInComponent} from './navbar-logged-in/navbar-logged-in.component';

import { AgmCoreModule } from '@agm/core';
import { HttpClientModule} from '@angular/common/http';
import { NgxGalleryModule } from '@kolkov/ngx-gallery'; 
import { HammerModule } from '@angular/platform-browser'; 
import {IvyCarouselModule} from 'angular-responsive-carousel';
import {SwiperModule} from 'swiper/angular';
import {MatMenuModule} from '@angular/material/menu';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FlexLayoutModule } from '@angular/flex-layout';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatToolbarModule} from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { HouseDetailsComponent } from './house-details/house-details.component';

import{RouterModule, Routes} from '@angular/router';

const appRoutes: Routes = [
  { path: 'home', component: HouseDetailsComponent},
  { path: 'details', component: GalleryPartComponent},
  
  { path: 'home/details', component: GalleryPartComponent},
  { path: 'about', component: GalleryPartComponent},
  { path: '', redirectTo: '/home', pathMatch: 'full'}
];

@NgModule({
  declarations: [
    AppComponent,
    GalleryPartComponent,
    UserContactComponent,
    HouseInfoComponent,
    MapComponent,
    PhotoCarouselComponent,
    NavbarComponent,
    NavbarLoggedInComponent,
    HouseDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule, 
    NgxGalleryModule , 
    HammerModule,
    RouterModule.forRoot(appRoutes),
    IvyCarouselModule,
    SwiperModule,
    MatIconModule,
    FlexLayoutModule,
    MatMenuModule, 
    MatToolbarModule, 
    MatButtonModule, 
    MatSidenavModule, 
    BrowserAnimationsModule,
    FormsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBBhqL0VAJqQpXMATFPcLdsq9RYV0kBjTQ',
     
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
