import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';

@Component({
  selector: 'app-reset-passwd',
  templateUrl: './reset-passwd.component.html',
  styleUrls: ['./reset-passwd.component.css']
})
export class ResetPasswdComponent implements OnInit {

  public hide = true;
  public hideConfirm = true;
  resetForm: FormGroup;
  newPasswd: string;
  confirmPasswd: string;

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.resetForm = this.fb.group({
      newPasswd: ['', [Validators.required, Validators.minLength(8)]],
      confirmPasswd: ['', [Validators.required, Validators.minLength(8)]]
    })
  }

  onSubmit() {
    if(this.resetForm.get('newPasswd').value !== this.resetForm.get('confirmPasswd').value) {
      alert('Passwords must match!')
    } else {
      this.newPasswd = this.resetForm.get('newPasswd').value
      this.confirmPasswd = this.resetForm.get('confirmPasswd').value
    }
  }
}
