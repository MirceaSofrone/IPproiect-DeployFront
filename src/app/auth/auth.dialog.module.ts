import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ResetPasswdComponent } from './components/reset-passwd/reset-passwd.component';
import { DialogWrapperComponent } from './components/dialog-wrapper/dialog-wrapper.component';
import { ForgotPasswdComponent } from './components/forgot-passwd/forgot-passwd.component';
import { SuccessComponent } from './components/success/success.component';

@NgModule({
  imports: [
    CommonModule,
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
            path: 'reset',
            component: ResetPasswdComponent,
          },
          {
            path: 'forgot',
            component: ForgotPasswdComponent
          },
          {
            path: 'success',
            component: SuccessComponent
          }
        ],
      },
    ]),
  ],
  declarations: [
  SuccessComponent],
  providers: []
})
export class DialogModule {}
