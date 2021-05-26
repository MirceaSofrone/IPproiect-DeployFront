import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
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
    newPass: '',
    token: ''
  };

  constructor(
    private fb: FormBuilder, 
    private router: Router,
    private readonly route: ActivatedRoute,
    private auth: AuthenticationService) { }

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
      console.log(this.token)
  }

  goToLogin(): void {
    this.router.navigate(['/login'])
  }

  onSubmit() {
    this.submitted=true;
    this.payload.token = this.token
    this.newPasswd = this.resetForm.get('newPasswd').value
    this.confirmPasswd = this.resetForm.get('confirmPasswd').value
    this.payload.newPass = this.newPasswd
    if (this.newPasswd !== this.confirmPasswd) {
      alert('Passwords must match!')
    } else {

      console.log(this.payload)
      this.auth.change(this.payload).subscribe(
        res => console.log(res),
        err => console.log(err)
      )
    }
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
