import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { LoginPayload } from 'src/app/auth/models/auth.model';
import { AuthenticationService } from 'src/app/auth/services/authentication/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  public hide = true;
  login: FormGroup;
  email: String;
  password: String;
  payload: LoginPayload={
    username:'',
    password:''
  };

  constructor(private form: FormBuilder,
    private router: Router,
    private auth: AuthenticationService,
    // public loginDialogRef: MatDialogRef<LoginComponent>,
    // @Inject(MAT_DIALOG_DATA) public loginData: any
    ) { }

  ngOnInit(): void {
    this.login=this.form.group(
      {
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(8)]]
      }
    )
  }

  goToRegister(): void {
    this.router.navigate(['dialog/register'], {
      skipLocationChange: true
    })
  }

  goToForgotPass(): void {
    this.router.navigate(['dialog/forgot'], {
      skipLocationChange: true
    })
  }

  onSubmit() {
    this.payload.username = this.login.get('email').value;
    this.payload.password = this.login.get('password').value;
    console.log(this.payload);
    this.auth.login(this.payload).subscribe(
      res => {
        localStorage.setItem('token', res.accessToken)
        alert('You successfully logged in!')
      },
      err => alert('Email or password are incorrect!')
    )
  }
}
