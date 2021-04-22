import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { LoginComponent } from 'src/app/auth/components/login/login.component';
import { Component } from '@angular/core';

@Component({
  selector: 'app-dialog',
  templateUrl: 'test.dialog.component.html',
  styleUrls: ['./test.dialog.component.css'],
})

export class DialogComponent {
  constructor(public dialog: MatDialog) {}

  openDialog() {
    const dialogRef = this.dialog.open(LoginComponent,
      {
        width:'75%',
        height:'80%',
        panelClass:'pclass'
      });

  }
}
