import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HouseDetailsComponent } from './house-details/house-details.component';
import {LandingPageComponent} from './landing-page/landing-page.component';
import { ListComponent } from './list/list.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AddHouseComponent } from './dashboard/add-house/add-house.component';
import { SellerDashboardComponent } from './dashboard/seller-dashboard/seller-dashboard.component';

const routes: Routes = [
  {  path: 'house-details/:id', component: HouseDetailsComponent },
  {  path: 'home' , component: LandingPageComponent},
  {  path: 'listing', component : ListComponent},
  {  path: 'dashboard', component : DashboardComponent },
  {  path: 'addHouse', component : AddHouseComponent },
  {  path: 'sellerDash', component : SellerDashboardComponent }
];


// const routes: Routes = [
//   // { path: 'listing', component: listing-page }
// >>>>>>> landing-integration
// ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
