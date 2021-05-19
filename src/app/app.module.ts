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
import { NgxChartsModule } from '@swimlane/ngx-charts';
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
import { FooterComponent } from './footer/footer.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SellerDashboardComponent } from './dashboard/seller-dashboard/seller-dashboard.component';
import { SellerPanelComponent } from './dashboard/seller-dashboard/seller-panel/seller-panel.component';
import { SellerStatisticsComponent } from './dashboard/seller-dashboard/seller-statistics/seller-statistics.component';
import { EditHouseComponent } from './dashboard/seller-dashboard/edit-house/edit-house.component';
import { EditFormComponent } from './dashboard/seller-dashboard/edit-house/edit-form/edit-form.component';
import { DashboardButtonsComponent } from './dashboard/global-components/dashboard-buttons/dashboard-buttons.component';
import { UserInfoComponent } from './dashboard/global-components/user-info/user-info.component';
import { HeaderComponent } from './dashboard/global-components/header/header.component';
import { UserStatisticsPanelComponent } from './dashboard/user-statistics-panel/user-statistics-panel.component';
import { UserFavoriteHistoryComponent } from './dashboard/user-favorite-history/user-favorite-history.component';
import { StatisticsPanelComponent } from './dashboard/user-statistics-panel/statistics-panel/statistics-panel.component';
import { FavoritePanelComponent } from './dashboard/user-favorite-history/favorite-panel/favorite-panel.component';
import { HistoryPanelComponent } from './dashboard/user-favorite-history/history-panel/history-panel.component';
import { AddHouseComponent } from './dashboard/add-house/add-house.component';
import { AddFormComponent } from './dashboard/add-house/add-form/add-form.component';
import { SellerHousePanelComponent } from './dashboard/seller-dashboard/seller-panel/seller-house-panel/seller-house-panel.component';
import { SellerStatisticsPanelComponent } from './dashboard/seller-dashboard/seller-statistics/seller-statistics-panel/seller-statistics-panel.component';
import { DashFooterComponent } from './dashboard/global-components/dash-footer/dash-footer.component';

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
    ListComponent,
    FooterComponent,
    DashboardComponent,
    SellerDashboardComponent,
    SellerPanelComponent,
    SellerStatisticsComponent,
    EditHouseComponent,
    EditFormComponent,
    DashboardButtonsComponent,
    UserInfoComponent,
    HeaderComponent,
    UserStatisticsPanelComponent,
    UserFavoriteHistoryComponent,
    StatisticsPanelComponent,
    FavoritePanelComponent,
    HistoryPanelComponent,
    AddHouseComponent,
    AddFormComponent,
    SellerHousePanelComponent,
    SellerStatisticsPanelComponent,
    DashFooterComponent

  ],
  imports: [
    NgxPaginationModule,
    NgxChartsModule,
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
