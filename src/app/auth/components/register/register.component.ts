import { Component, Inject,OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterPayload } from 'src/app/auth/models/auth.model';
import { MatDialogRef, MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AuthenticationService } from 'src/app/auth/services/authentication/authentication.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public hide = true;
  public hideConfirm = true;
  userType: string;
  payload: RegisterPayload = {
    password: '',
    firstName: '',
    lastName: '',
    phone: '',
    email: '',
    userType: ''
  }
  registerForm: FormGroup;
  confirmPasswd: string;

  constructor(private fb: FormBuilder, private auth: AuthenticationService,public registerDialogRef: MatDialogRef<RegisterComponent>,
    @Inject(MAT_DIALOG_DATA) public registerData: any
    ) { }
    onNoClick(): void{
      this.registerDialogRef.close();
    }
  
  ngOnInit(): void {
    this.registerForm = this.fb.group({
      userType: ['',[]],
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      emailAddress: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(10)]],
      passwd: ['', [Validators.required, Validators.minLength(8)]],
      confirmPasswd: ['', [Validators.required, Validators.minLength(8)]],
    }
    )
  }

 
  onSubmit() {
    if (this.registerForm.get('passwd').value !== this.registerForm.get("confirmPasswd").value) {
        alert("Passwords must match!")
    } else if(this.registerForm.get('userType').value === '') {
        alert("userType must be selected")
    } else {
        this.payload.password = this.registerForm.get('passwd').value
        this.payload.userType = this.registerForm.get('userType').value
        this.payload.firstName = this.registerForm.get('firstName').value
        this.payload.lastName = this.registerForm.get('lastName').value
        this.payload.email = this.registerForm.get('emailAddress').value
        this.payload.phone = this.registerForm.get('phoneNumber').value

        this.auth.register(this.payload).subscribe(
          res => {
            if(res.success === true) {
              console.log('success')
            }
          },
          err => console.log(err)
        )
    }
  }

}
