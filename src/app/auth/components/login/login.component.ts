import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { LoginPayload } from 'src/app/auth/models/auth.model';

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

  constructor(private form: FormBuilder) { }


  ngOnInit(): void {
    this.login=this.form.group(
      {
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(8)]]
      }
    )
  }
  onSubmit() {
    this.payload.email = this.login.get('email').value;
    this.payload.password = this.login.get('password').value;
    console.log(this.payload);
  }
}
