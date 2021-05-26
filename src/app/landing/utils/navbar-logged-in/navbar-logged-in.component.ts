import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/auth/services/authentication/authentication.service';



@Component({
  selector: 'app-navbar-logged-in',
  templateUrl: './navbar-logged-in.component.html',
  styleUrls: ['./navbar-logged-in.component.css']
})




export class NavbarLoggedInComponent implements OnInit {

  profileRoute: string;
  constructor(private auth: AuthenticationService, private router: Router) { }

  ngOnInit(): void {

  }

  checkRoute(): void {
    if(this.auth.isAuthenticated()) {
      this.router.navigate(['/dashboard'])
    } else {
      this.router.navigate(['/login'])
    }
  }

  checkAuthOrForum(): void {
    if(this.auth.isAuthenticated()) {
      this.router.navigate(['/forum'])
    } else {
      this.router.navigate(['/login'])
    }
  }

  logout(): void {
    this.auth.logout()
  }

}

