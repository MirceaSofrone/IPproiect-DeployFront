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
  user : IUser = {
    userId: localStorage.getItem('userID'),
    name: "",
    email: "",
    phoneNumber:""
  };

  constructor(private http: HttpClient){
    const headers = {'Authorization': 'Bearer ' + localStorage.getItem('token') };

    console.log(headers);
    this.http.get<IUser>(this.getUserInfo + localStorage.getItem('userID'), { headers }).subscribe(data => {this.user = data;
    if(this.user.email == null) this.user.email = "no email address";
    if(this.user.phoneNumber == null) this.user.phoneNumber = "no phone number";
    console.log("GET USER INFO");
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

     
      const headers = {'Authorization': 'Bearer ' + localStorage.getItem('token')  };
      this.http.post<IUser>(this.updateUserInfoUrl,this.user, { headers }).subscribe(data => {this.user = data;
      if(this.user.email == null) this.user.email = "no email address";
      if(this.user.phoneNumber == null) this.user.phoneNumber = "no phone number";
    });
    
    }
}
