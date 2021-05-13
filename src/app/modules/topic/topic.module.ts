import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatFormFieldModule } from '@angular/material/form-field';
import { ExtendedPostComponent } from './components/extended-post/extended-post.component';
import { ForumTopicComponent } from './components/forum-topic/forum-topic.component';
import { AnswerComponent } from './components/answer/answer.component';
import { MatIconModule} from '@angular/material/icon';
import { RouterModule } from '@angular/router';
import { AnswersListComponent } from './components/answers-list/answers-list.component';
import { MatCardModule} from '@angular/material/card';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';
import { QuestionPageComponent } from '../add-question/components/question-page/question-page.component';
@NgModule({
  declarations: [
    ExtendedPostComponent,
    ForumTopicComponent,
     AnswerComponent,
     AnswersListComponent,
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
export class TopicModule { }
