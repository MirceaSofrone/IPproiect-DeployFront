import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { LoginComponent } from 'src/app/auth/components/login/login.component';
import { RegisterComponent } from 'src/app/auth/components/register/register.component';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dialog',
  templateUrl: 'test.dialog.component.html',
  styleUrls: ['./test.dialog.component.css'],
})

export class DialogComponent {
  constructor(public router: Router) {}

  openAuthDialog() {
    this.router.navigate(['/dialog']);
  }
}
