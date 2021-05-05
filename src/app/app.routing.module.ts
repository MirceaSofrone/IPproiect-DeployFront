import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';

const routes: Routes = [
    {
        path: '',
        component: AppComponent
    },
    {
        path: 'dialog',
        loadChildren: () => 
            import('./auth/auth.dialog.module').then(
                m => m.DialogModule
            )
    }
]

@NgModule ({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule {}