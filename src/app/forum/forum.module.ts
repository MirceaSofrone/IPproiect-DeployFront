import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { QuestionPageComponent } from './modules/add-question/components/question-page/question-page.component';
import { QuestionComponent } from './modules/add-question/components/question/question.component';
import { EditorPageComponent } from './modules/editor/components/editor-page/editor-page.component';
import { EditorComponent } from './modules/editor/components/editor/editor.component';
import { ArticlesListComponent } from './modules/home/components/articles-list/articles-list.component';
import { ForumComponent } from './modules/home/components/forum/forum.component';
import { PostComponent } from './modules/home/components/post/post.component';
import { AnswerComponent } from './modules/topic/components/answer/answer.component';
import { AnswersListComponent } from './modules/topic/components/answers-list/answers-list.component';
import { ExtendedPostComponent } from './modules/topic/components/extended-post/extended-post.component';
import { ForumTopicComponent } from './modules/topic/components/forum-topic/forum-topic.component';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    QuestionComponent,
    QuestionPageComponent,
    EditorComponent,
    EditorPageComponent,
    ArticlesListComponent,
    ForumComponent,
    PostComponent,
    AnswerComponent,
    AnswersListComponent,
    ExtendedPostComponent,
    ForumTopicComponent
  ],
  imports: [
    ReactiveFormsModule,
    FormsModule,
    RouterModule,
    CommonModule,
    //ArticlesListComponent,
    //ForumComponent,
    //PostComponent,
    MatIconModule,
    MatCardModule,
    MatDividerModule,
    MatButtonModule,
    MatFormFieldModule
  ]
})
export class ForumModule { }
