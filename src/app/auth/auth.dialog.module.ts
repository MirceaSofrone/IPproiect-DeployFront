import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ResetPasswdComponent } from './components/reset-passwd/reset-passwd.component';
import { DialogWrapperComponent } from './components/dialog-wrapper/dialog-wrapper.component';
import { ForgotPasswdComponent } from './components/forgot-passwd/forgot-passwd.component';
import { SuccessComponent } from './components/success/success.component';
import { AuthMaterialModule } from './auth.material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { RegisterConfirmationComponent } from './components/register-confirmation/register-confirmation.component';

@NgModule({
  imports: [
    CommonModule,
    AuthMaterialModule,
    ReactiveFormsModule,
    RouterModule.forChild([
      {
        path: '',
        component: DialogWrapperComponent,
        children: [
          {
            path: 'login',
            component: LoginComponent,
          },
          {
            path: 'register',
            component: RegisterComponent,
          },
          {
            path: 'reset/:token',
            component: ResetPasswdComponent,
          },
          {
            path: 'forgot',
            component: ForgotPasswdComponent
          },
          {
            path: 'success',
            component: SuccessComponent
          },
          {
            path: 'confirmation/:token',
            component: RegisterConfirmationComponent
          }
        ],
      },
    ]),
  ],
  declarations: [
    LoginComponent,
    RegisterComponent,
    ResetPasswdComponent,
    ForgotPasswdComponent,
    SuccessComponent,
    DialogWrapperComponent,
    RegisterConfirmationComponent
  ],
  providers: []
})
export class DialogModule {}
