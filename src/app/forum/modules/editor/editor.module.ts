import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EditorComponent } from './components/editor/editor.component';
import { EditorPageComponent } from './components/editor-page/editor-page.component';

@NgModule({
  declarations: [
    EditorComponent,
    EditorPageComponent
  ],
  imports: [
    CommonModule
  ]
})
export class EditorModule { }
