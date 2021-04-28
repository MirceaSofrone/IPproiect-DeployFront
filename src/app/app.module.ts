import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MapComponent } from './map/map.component';
import { AgmCoreModule } from '@agm/core';
import { ListComponent } from './list/list.component';
import {HttpClientModule} from '@angular/common/http'
import {NgxPaginationModule} from 'ngx-pagination'; 
import {FormsModule} from '@angular/forms';
import{ReactiveFormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatIcon, MatIconModule} from '@angular/material/icon';
@NgModule({
  declarations: [
    AppComponent,
    MapComponent,
    ListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgxPaginationModule,
    MatInputModule,
    MatIconModule,
    AppRoutingModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBBhqL0VAJqQpXMATFPcLdsq9RYV0kBjTQ',
     
    }),
   HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
