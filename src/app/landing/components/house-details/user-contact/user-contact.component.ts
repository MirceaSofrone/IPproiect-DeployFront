import {Component, Inject, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { AuthenticationService } from 'src/app/auth/services/authentication/authentication.service';
import {Subscription} from 'rxjs';
import {ActivatedRoute} from '@angular/router';
import {WishlistService} from '../../../service/wishlist.service';
import {ListComponent} from '../../list/list.component';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-user-contact',
  templateUrl: './user-contact.component.html',
  styleUrls: ['./user-contact.component.css']
})
export class UserContactComponent implements OnInit {

  private routeSub: Subscription;
  houseID: any;
  result: any;
  wishlist: number[] = [];
  constructor(  private snackbar: MatSnackBar, private wishlistService: WishlistService, private route: ActivatedRoute, private http: HttpClient, private authService: AuthenticationService) { }

  ngOnInit(): void {
    this.routeSub = this.route.params.subscribe(params => {
      this.houseID = params.id;

    });
    this.wishlistService.getWishlist().subscribe(result => {
      this.wishlist = result;

    });
  }

   onSubmit(data){

if (this.authService.isAuthenticated()){


    const bearer = localStorage.getItem('token');
    const token = `Bearer ${bearer}`;

    const httpHeaders = new HttpHeaders({
       'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
       Authorization: token });
    // const httpHeaders = new HttpHeaders();
    // httpHeaders.append('content-type', 'application/json');
    // httpHeaders.append('Authorization', token);
    const message = data.form.value.message;


    const userEmail = localStorage.getItem('userID');
    const sellerEmail = localStorage.getItem('sellerID');
    const sendData = {
      idClient : userEmail,
      idSeller : sellerEmail,
      message
    };
console.log(sendData, httpHeaders);
    this.http.post('https://back-end-hpp.herokuapp.com/api/v1/feedback', sendData, {headers: httpHeaders })
     .subscribe((result) => {

     });

}
else{
  this.snackbar.open('Please Login!', 'Close', {
    duration: 3000
  })
}
  }

  addToFavorite() {
    if (this.authService.isAuthenticated()) {
      this.wishlistService.getWishlist().subscribe(result => {
        this.wishlist = result;

      });
      if (!this.wishlist.includes(this.houseID)) {
      this.wishlistService.addToWishlist(this.houseID).subscribe((result) => {

      });
        this.snackbar.open('Added to favorites', 'Close', {
          duration: 3000
        })

      }
      else {
        this.wishlistService.removeFromWishlist(this.houseID).subscribe((result) => {


        });
        this.snackbar.open('Removed from favorites', 'Close', {
          duration: 3000
        })

      }
    }
    else{
      this.snackbar.open('Please Login!', 'Close', {
        duration: 3000
      })
    }
  }
}
