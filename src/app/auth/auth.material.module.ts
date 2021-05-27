import { NgModule } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { LayoutModule } from '@angular/cdk/layout';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatDialogModule } from '@angular/material/dialog';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatSnackBarModule } from '@angular/material/snack-bar';
@NgModule({
    imports: [
        MatButtonModule,
        MatFormFieldModule,
        MatInputModule,
        LayoutModule,
        MatIconModule,
        MatButtonToggleModule,
        MatDialogModule,
        MatCheckboxModule,
        MatSnackBarModule
    ],
    exports: [
        MatFormFieldModule,
        MatInputModule,
        LayoutModule,
        MatButtonModule,
        MatIconModule,
        MatButtonToggleModule,
        MatDialogModule,
        MatCheckboxModule,
        MatSnackBarModule
    ]
})

export class AuthMaterialModule {}
