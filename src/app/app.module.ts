import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';




import SearchIcon from '@material-ui/icons/Search';

import { AppComponent } from './app.component';
import { GalleryPartComponent } from './gallery-part/gallery-part.component';
import {UserContactComponent} from './user-contact/user-contact.component';
import {HouseInfoComponent} from './house-info/house-info.component';
import {MapComponent} from './map/map.component';
import { PhotoCarouselComponent } from './photo-carousel/photo-carousel.component';
import {NavbarComponent} from './navbar/navbar.component';
import {NavbarLoggedInComponent} from './navbar-logged-in/navbar-logged-in.component';

import { AgmCoreModule } from '@agm/core';

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

import { HouseDetailsComponent } from './house-details/house-details.component';


import {RouterModule, Routes} from '@angular/router';


import { LandingSearchComponent } from './landing-search/landing-search.component';
import {FormsModule} from '@angular/forms';
import {ReactiveFormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatIcon, MatIconModule} from '@angular/material/icon';

import { HttpClientModule } from '@angular/common/http';
import { LandingBottomComponent } from './landing-bottom/landing-bottom.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { AppRoutingModule } from './app-routing.module';
import { ListComponent } from './list/list.component';
import {NgxPaginationModule} from "ngx-pagination";

const appRoutes: Routes = [
  { path: 'home', component: HouseDetailsComponent},
  { path: 'details', component: GalleryPartComponent},
  { path: 'favorites', component: HouseDetailsComponent},
  { path: 'about', component: HouseDetailsComponent},
  { path: 'account', component: HouseDetailsComponent},
  { path: 'home/:id', component: HouseDetailsComponent},
  { path: 'listing', component: ListComponent},
  // { path: 'home/favorites', component: HouseDetailsComponent},
  // { path: 'home/about', component: HouseDetailsComponent},

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
    HouseDetailsComponent,
    LandingSearchComponent,
    LandingBottomComponent,
    LandingPageComponent,
    ListComponent

  ],
  imports: [
    NgxPaginationModule,
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

    }),

    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    MatInputModule,
    MatIconModule,
    AppRoutingModule

  ],


  providers: [],
  bootstrap: [AppComponent],
 

})
export class AppModule { 
   }
