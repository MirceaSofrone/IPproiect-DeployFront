import { Component, ViewChild, TemplateRef, AfterViewInit, OnDestroy } from "@angular/core";
import { Router, ActivatedRoute, NavigationEnd } from "@angular/router";

import { MatDialog } from "@angular/material/dialog";

import { take } from "rxjs/operators"; 
 
@Component({
  selector: 'app-dialog-wrapper',
  templateUrl: './dialog-wrapper.component.html',
  styleUrls: ['./dialog-wrapper.component.css']
})
export class DialogWrapperComponent implements AfterViewInit, OnDestroy {
  @ViewChild("dialogTemplate") dialogTemplate: TemplateRef<any>;

  currentRoute: string;
  dialogRef: any;
  constructor(
    private dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngAfterViewInit(): void {
    this.dispatchDialog();
  }

  dispatchDialog(): void {
    this.dialogRef = this.dialog.open(this.dialogTemplate, {
      disableClose: false,
      panelClass: 'pclass'
    });

    this.dialogRef
      .afterOpened()
      .pipe(take(1))
      .subscribe(() => {
        this.router.navigate([this.router.url], {
          relativeTo: this.route,
          // skipLocationChange: true
        });
      });

    this.dialogRef
      .afterClosed()
      .pipe(take(1))
      .subscribe(() => {
        this.router.navigate(["/"]);
      });
  }

  ngOnDestroy(): void {
    this.dialog.closeAll();
  }
  
}
