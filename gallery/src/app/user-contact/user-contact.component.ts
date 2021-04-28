import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
@Component({
  selector: 'app-user-contact',
  templateUrl: './user-contact.component.html',
  styleUrls: ['./user-contact.component.css']
})
export class UserContactComponent implements OnInit {

  constructor(private http: HttpClient ) { }

  ngOnInit(): void {
  }
  // tslint:disable-next-line:typedef
   onSubmit(data){
   this.http.post('https://607af93abd56a60017ba3474.mockapi.io/api/v1/emails', data.form.value)
     .subscribe((result) => {
       console.warn('result', result);
     });
   console.warn(data.form.value);

  }
}
