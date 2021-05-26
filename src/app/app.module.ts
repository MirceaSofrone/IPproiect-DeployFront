import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import SearchIcon from '@material-ui/icons/Search';
import { AppComponent } from './app.component';
import { AgmCoreModule } from '@agm/core';
import {IvyCarouselModule} from 'angular-responsive-carousel';
import {SwiperModule} from 'swiper/angular';
import {MatMenuModule} from '@angular/material/menu';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FlexLayoutModule } from '@angular/flex-layout';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatToolbarModule} from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import {RouterModule, Routes} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {ReactiveFormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatIcon, MatIconModule} from '@angular/material/icon';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';

import {LandingModule} from './landing/landing.module';
import { DialogModule } from './auth/auth.dialog.module';
import { AuthMaterialModule } from './auth/auth.material.module';
import { DialogComponent } from './auth/components/test-dialog/test.dialog.component';
import {MatListModule} from '@angular/material/list';
import {NavbarLoggedInComponent} from './landing/utils/navbar-logged-in/navbar-logged-in.component';
import {DashboardModule} from './dashboard/dashboard.module';
import { ForumModule } from './forum/forum.module';


@NgModule({
  declarations: [
    AppComponent,
    DialogComponent,
    NavbarLoggedInComponent

  ],
  imports: [
    ForumModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
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
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    MatInputModule,
    MatIconModule,
    LandingModule,
    AppRoutingModule,

    DashboardModule,
AuthMaterialModule,
    DialogModule,
    MatListModule,
    RouterModule,

  ],

  providers: [],
  bootstrap: [AppComponent],
})

export class AppModule {
   }
