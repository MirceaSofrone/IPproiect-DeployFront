import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public hide = true;
  public hideConfirm = true;
  registerForm: FormGroup;
  firstName: string;
  lastName: string;
  emailAddress: string;
  phoneNumber: string;
  passwd: string;
  confirmPasswd: string;

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      emailAddress: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(10)]],
      passwd: ['', [Validators.required, Validators.minLength(8)]],
      confirmPasswd: ['', [Validators.required, Validators.minLength(8)]]
    }
    )
  }

  ngOnSubmit() {
    if (this.registerForm.get('passwd').value !== this.registerForm.get("confirmPasswd").value) {
      alert("Passwords must match!")
    }
    else {
      this.passwd = this.registerForm.get('passwd').value
      this.confirmPasswd = this.registerForm.get('confirmPasswd').value
    }
  }

}
