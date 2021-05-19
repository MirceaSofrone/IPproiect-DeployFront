import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';


import { AppRoutingModule } from './app-routing.module';
import {LandingModule} from './landing/landing.module';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    AppRoutingModule,
    LandingModule

  ],

  providers: [],
  bootstrap: [AppComponent],


})
export class AppModule {
   }
