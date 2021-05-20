import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IUser } from './user-type';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent {
  private getUserInfo = 'https://back-end-hpp.herokuapp.com/api/v1/users/';
  private updateUserInfoUrl = 'https://back-end-hpp.herokuapp.com/api/v1/users/update';
  changeUserDetails : boolean = false;
  userToken : string = '7';
  user : IUser = {
    userId: 7,
    name: "",
    email: "",
    phoneNumber:""
  };

  constructor(private http: HttpClient){
    const token = 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXdITHFUbHoybnZIMzdKZzFSd1lJNEJab2xBdVZVYXNBT1Jab2ZiSVBVPSIsImlhdCI6MTYyMTUxMTczNSwiZXhwIjoxNjIxNTk4MTM1fQ.LMXMHhBV9m-UtXjALX1ikjrOHYb9aEsmh-5SXyB6OAEEVe1Wl9wYfznzo5SSY-XkNeXu-4Z4yt5WsUs-vWac8A';
    const headers = {'Authorization': token };
    this.http.get<IUser>(this.getUserInfo.concat(this.userToken), { headers }).subscribe(data => {this.user = data;
    if(this.user.email == null) this.user.email = "no email address";
    if(this.user.phoneNumber == null) this.user.phoneNumber = "no phone number";
  }); }

  updateUserInfo():void{
    let editSection = (<HTMLInputElement>document.querySelector('.edit__info__form'));
    let userInfoSection = (<HTMLInputElement>document.querySelector('.acc__info'));
    editSection.classList.remove('hide');
    userInfoSection.classList.add('hide'); 
    this.changeUserDetails=true;
    }

    saveChanges(){
      this.changeUserDetails = false;
      let editSection = (<HTMLInputElement>document.querySelector('.edit__info__form'));
      let userInfoSection = (<HTMLInputElement>document.querySelector('.acc__info'));
      editSection.classList.add('hide');
      userInfoSection.classList.remove('hide');

      const token = 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXdITHFUbHoybnZIMzdKZzFSd1lJNEJab2xBdVZVYXNBT1Jab2ZiSVBVPSIsImlhdCI6MTYyMTUxMTczNSwiZXhwIjoxNjIxNTk4MTM1fQ.LMXMHhBV9m-UtXjALX1ikjrOHYb9aEsmh-5SXyB6OAEEVe1Wl9wYfznzo5SSY-XkNeXu-4Z4yt5WsUs-vWac8A';
      const headers = {'Authorization': token };
      this.http.post<IUser>(this.updateUserInfoUrl,this.user, { headers }).subscribe(data => {this.user = data;
      if(this.user.email == null) this.user.email = "no email address";
      if(this.user.phoneNumber == null) this.user.phoneNumber = "no phone number";
    });
    
    }
}
