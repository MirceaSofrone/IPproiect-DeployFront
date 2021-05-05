import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MaterialModule } from './material.module';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './auth/components/login/login.component';
import { RegisterComponent } from './auth/components/register/register.component';
import { DialogResetPasswd, ResetPasswdComponent } from './auth/components/reset-passwd/reset-passwd.component';
import { DialogComponent } from './auth/components/test-dialog/test.dialog.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DialogWrapperComponent } from './auth/components/dialog-wrapper/dialog-wrapper.component';
import { AppRoutingModule } from './app.routing.module';
import { ForgotPasswdComponent } from './auth/components/forgot-passwd/forgot-passwd.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DialogComponent,
    RegisterComponent,
    ResetPasswdComponent,
    DialogResetPasswd,
    DialogWrapperComponent,
    ForgotPasswdComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
