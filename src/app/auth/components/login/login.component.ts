import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { LoginPayload } from 'src/app/auth/models/auth.model';
import { MatDialogRef, MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AuthenticationService } from 'src/app/auth/services/authentication/authentication.service';

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
    email:'',
    password:''
  };

  constructor(private form: FormBuilder,
    private auth: AuthenticationService,
    public loginDialogRef: MatDialogRef<LoginComponent>,
    @Inject(MAT_DIALOG_DATA) public loginData: any
    ) { }

  ngOnInit(): void {
    this.login=this.form.group(
      {
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(8)]]
      }
    )
  }

  onNoClick(): void{
    this.loginDialogRef.close();
  }

  onSubmit() {
    this.payload.email = this.login.get('email').value;
    this.payload.password = this.login.get('password').value;
    // console.log(this.payload);
    this.auth.login(this.payload).subscribe(
      res => {
        if(res.success === true) {
          localStorage.setItem('token', res.token)
          console.log(this.auth.isAuthenticated())
          console.log(res.success)

        }
      },
      err => console.log(err)
    )
  }
}
