import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/authentication/authentication.service';

@Component({
  selector: 'app-forgot-passwd',
  templateUrl: './forgot-passwd.component.html',
  styleUrls: ['./forgot-passwd.component.css']
})
export class ForgotPasswdComponent implements OnInit {

  forgotForm: FormGroup;
  email: String;
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private auth: AuthenticationService,
    private snackbar: MatSnackBar
    ) { }

  ngOnInit(): void {
    this.forgotForm = this.fb.group(
      {
        email: ['',[Validators.required, Validators.email]]
      }
    )
  }

  goToLogin(): void {
    this.router.navigate(['/login'])
  }

  onSubmit(): void {
    this.email = this.forgotForm.get('email').value;
    this.auth.reset(this.email).subscribe(
      res => {
        if(res.message.includes('Password reset token email sent')) {
          this.snackbar.open('Good! Now check your email!', 'Close', {
            duration: 3000
          })
        this.router.navigate["/"]
        } else if(res.message.includes('Bad email!')) {
          this.snackbar.open('This email is not registered. Try another one!', 'Close', {
            duration: 5000
          })
        }
      },
      err => {
        this.snackbar.open('Oops! Something went wrong, please try again!', 'Close', {
          duration: 5000
        })
      }
    )
  }

}
