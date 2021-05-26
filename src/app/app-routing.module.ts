import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DialogModule } from 'src/app/auth/auth.dialog.module'
import { HouseDetailsComponent } from './landing/components/house-details/house-details.component';
import {LandingPageComponent} from './landing/components/landing-page/landing-page.component';
import { ListComponent } from './landing/components/list/list.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AddHouseComponent } from './dashboard/add-house/add-house.component';
import { SellerDashboardComponent } from './dashboard/seller-dashboard/seller-dashboard.component';
import { EditHouseComponent } from './dashboard/seller-dashboard/edit-house/edit-house.component';
import { ForumComponent } from './forum/modules/home/components/forum/forum.component';
import { QuestionPageComponent } from './forum/modules/add-question/components/question-page/question-page.component';
import { EditorPageComponent } from './forum/modules/editor/components/editor-page/editor-page.component';
import { ForumTopicComponent } from './forum/modules/topic/components/forum-topic/forum-topic.component';
const routes: Routes = [

  { path: 'home', component: DashboardComponent},

  { path: '', component: LandingPageComponent},

  { path: 'house-details/:id', component: HouseDetailsComponent},
  { path: 'listing', component: ListComponent},
  {  path: 'dashboard', component : DashboardComponent },
  {  path: 'addHouse', component : AddHouseComponent },
  {  path: 'editHouse', component : EditHouseComponent },
  {  path: 'sellerDash', component : SellerDashboardComponent },
   { path: 'forum', component: ForumComponent },
   {
    path: 'add-question',
     component: QuestionPageComponent
  },
  {
    path: 'forum-topic/:id/answer',
    component: EditorPageComponent
  },
  {
    path: 'forum-topic/:id',
    component: ForumTopicComponent
  },
  // { path: '', redirectTo: '', pathMatch: 'full'}
  {
      path: 'dialog',
      loadChildren: () => 
          import('./auth/auth.dialog.module').then(
              m => m.DialogModule
          )
  }
];



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
