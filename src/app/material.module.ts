import { NgModule } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { LayoutModule } from '@angular/cdk/layout';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';


@NgModule({
    imports: [
        MatButtonModule,
        MatFormFieldModule,
        MatInputModule,
        LayoutModule,
        MatIconModule
    ],
    exports: [
        MatFormFieldModule,
        MatInputModule,
        LayoutModule,
        MatButtonModule,
        MatIconModule        
    ]
})

export class MaterialModule {}
