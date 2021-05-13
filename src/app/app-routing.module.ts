import { NgModule } from '@angular/core';
import { RouterModule, Routes, PreloadAllModules } from '@angular/router';
import { AppComponent } from './app.component';
import { AddQuestionModule } from './modules/add-question/add-question.module';
import { QuestionPageComponent } from './modules/add-question/components/question-page/question-page.component';
import { QuestionComponent } from './modules/add-question/components/question/question.component';
import { EditorPageComponent } from './modules/editor/components/editor-page/editor-page.component';
import { EditorComponent } from './modules/editor/components/editor/editor.component';
import { ForumComponent } from './modules/home/components/forum/forum.component';
import { HomeModule } from './modules/home/home.module';
import { AnswerComponent } from './modules/topic/components/answer/answer.component';
import { ExtendedPostComponent } from './modules/topic/components/extended-post/extended-post.component';
import { ForumTopicComponent } from './modules/topic/components/forum-topic/forum-topic.component';
import { TopicModule } from './modules/topic/topic.module';
const routes: Routes = [

  {
    path: '',
    component: ForumComponent
  }, 
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
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes, {preloadingStrategy: PreloadAllModules})],
  exports: [RouterModule],
  
})
export class AppRoutingModule { }
