import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DummyComponent } from './dummy/dummy.component';

const routes: Routes = [
  { path: 'home', component:DummyComponent },
  { path: 'login', component:DummyComponent },
  { path: 'account', component:DummyComponent },
  { path: 'wishlist', component:DummyComponent },
  { path: 'settings', component:DummyComponent },
  { path: 'profile', component:DummyComponent },
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
