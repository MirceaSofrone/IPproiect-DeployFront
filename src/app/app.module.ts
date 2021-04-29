import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import SearchIcon from '@material-ui/icons/Search';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LandingSearchComponent } from './landing-search/landing-search.component';
import {FormsModule} from '@angular/forms';
import {ReactiveFormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatIcon, MatIconModule} from '@angular/material/icon';

import { HttpClientModule } from '@angular/common/http';
import { LandingBottomComponent } from './landing-bottom/landing-bottom.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { AppRoutingModule } from './app-routing.module';


@NgModule({
  declarations: [
    AppComponent,

    LandingSearchComponent,

    LandingBottomComponent,

    LandingPageComponent
  ],
  imports: [
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
export class AppModule { }
