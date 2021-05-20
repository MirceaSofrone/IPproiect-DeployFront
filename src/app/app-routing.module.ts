import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HouseDetailsComponent } from './landing/components/house-details/house-details.component';
import {LandingPageComponent} from './landing/components/landing-page/landing-page.component';
import { ListComponent } from './landing/components/list/list.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AddHouseComponent } from './dashboard/add-house/add-house.component';
import { SellerDashboardComponent } from './dashboard/seller-dashboard/seller-dashboard.component';

const routes: Routes = [
  { path: '', component: LandingPageComponent},
  { path: 'house-details/:id', component: HouseDetailsComponent},
  { path: 'listing', component: ListComponent},

  {  path: 'dashboard', component : DashboardComponent },
  {  path: 'addHouse', component : AddHouseComponent },
  {  path: 'sellerDash', component : SellerDashboardComponent },
  // { path: '', redirectTo: '', pathMatch: 'full'}
];



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
