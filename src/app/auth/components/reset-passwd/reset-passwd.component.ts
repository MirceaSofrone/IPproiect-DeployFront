import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router'
import { Subscription } from 'rxjs';
import { ChangePayload } from '../../models/auth.model';
import { AuthenticationService } from '../../services/authentication/authentication.service';

@Component({
  selector: 'app-reset-passwd',
  templateUrl: './reset-passwd.component.html',
  styleUrls: ['./reset-passwd.component.css']
})
export class ResetPasswdComponent implements OnInit {

  submitted = false;
  public hide = true;
  public hideConfirm = true;
  resetForm: FormGroup;
  newPasswd: string;
  confirmPasswd: string;
  subscription: Subscription;
  token: string;
  payload: ChangePayload = {
    token: '',
    password: ''
  };

  constructor(
    private fb: FormBuilder, 
    private router: Router,
    private readonly route: ActivatedRoute,
    private auth: AuthenticationService,
    private snackbar: MatSnackBar) { }

  ngOnInit(): void {
    this.resetForm = this.fb.group({
      newPasswd: ['', [Validators.required, Validators.minLength(8)]],
      confirmPasswd: ['', [Validators.required, Validators.minLength(8)]]
    })

    this.subscription = this.route.params.subscribe (
      params => {
        this.token = params['token']
      }
    )
  }

  goToLogin(): void {
    this.router.navigate(['/login'])
  }

  onSubmit() {
    this.submitted=true;
    this.payload.token = this.token
    this.newPasswd = this.resetForm.get('newPasswd').value
    this.confirmPasswd = this.resetForm.get('confirmPasswd').value
    this.payload.password = this.newPasswd
    if (this.newPasswd !== this.confirmPasswd) {
      this.snackbar.open('Passwords must match!', 'Close', {
        duration: 5000
      })
    } else {
      this.auth.change(this.payload).subscribe(
        res => {
          if(res.message === 'Password changed successfully') {
            this.snackbar.open('Your password has been changed successfully! Now you can login!', 'Close', {
              duration: 3000
            })
          }
          this.router.navigate(['/login'])
        },
        err => {
          this.snackbar.open('Oops! Something went wrong, please try again!', 'Close', {
            duration: 5000
          })
        }
      )
    }
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
