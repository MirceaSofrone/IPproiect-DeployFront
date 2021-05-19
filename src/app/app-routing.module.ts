import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HouseDetailsComponent } from './landing/components/house-details/house-details.component';
import {LandingPageComponent} from './landing/components/landing-page/landing-page.component';
import {ListComponent} from './landing/components/list/list.component';


const routes: Routes = [
  {  path: 'house-details/:id', component: HouseDetailsComponent },
  { path: 'home' , component: LandingPageComponent},
  { path: 'favorites', component: HouseDetailsComponent},
  { path: 'about', component: HouseDetailsComponent},
  { path: 'account', component: HouseDetailsComponent},
  { path: 'listing', component: ListComponent},

  { path: '', redirectTo: '/home', pathMatch: 'full'}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
