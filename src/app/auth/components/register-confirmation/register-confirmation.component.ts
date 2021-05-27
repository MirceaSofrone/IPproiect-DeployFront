import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthenticationService } from '../../services/authentication/authentication.service';

@Component({
  selector: 'app-register-confirmation',
  templateUrl: './register-confirmation.component.html',
  styleUrls: ['./register-confirmation.component.css']
})
export class RegisterConfirmationComponent implements OnInit, OnDestroy {

  token: String;
  subscription: Subscription;
  constructor(
    private router: Router,
    private auth: AuthenticationService,
    private readonly route: ActivatedRoute,
    private snackbar: MatSnackBar) {
    }
    
  ngOnInit(): void {
      this.subscription = this.route.params.subscribe (
        params => {
          this.token = params['token']
        }
      )
      console.log(this.token)

    this.auth.activate(this.token).subscribe(
      res => {
        if(res.message === 'Succesgully confirmed!') {
          this.snackbar.open('Now you can LogIn!', 'Close', {
            duration: 4000
          })
          this.router.navigate(["/login"])
        }    
      },
      err => {
        this.snackbar.open('Oops! Something went wrong, please try again!', 'Close', {
          duration: 5000
        })
      }
    )
  }

  goToLogin(): void {
    this.router.navigate(['/login'])
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe()
  }
}
