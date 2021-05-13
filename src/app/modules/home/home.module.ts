import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ArticlesListComponent } from './components/articles-list/articles-list.component';
import { ForumComponent } from './components/forum/forum.component';
import { PostComponent } from './components/post/post.component';

@NgModule({
  declarations: [
    // ArticlesListComponent,
    // ForumComponent,
    // PostComponent
  ],
  imports: [
    CommonModule,
    MatIconModule,
    RouterModule,
    MatCardModule,
    MatDividerModule,
    MatButtonModule,
    MatFormFieldModule
  ]
})
export class HomeModule { }
