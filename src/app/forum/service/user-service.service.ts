import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }

  public getID(): number {
    let usertoken = localStorage.getItem("userID");
    return parseInt(usertoken);
  }

  public getToken(): string {
  
   return ("Bearer " + localStorage.getItem("token"));
  }
}

