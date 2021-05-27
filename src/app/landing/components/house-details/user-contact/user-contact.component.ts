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
      console.log(this.houseID, 'house id');
    });
    this.wishlistService.getWishlist().subscribe(result => {
      this.wishlist = result;
      console.log(result, 'wishlist');
    });
  }

   onSubmit(data){

if (this.authService.isAuthenticated()){


    const bearer = localStorage.getItem('token');
    const token = `Bearer ${bearer}`;
    console.log(token);
    const httpHeaders = new HttpHeaders({
       'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
       Authorization: token });
    // const httpHeaders = new HttpHeaders();
    // httpHeaders.append('content-type', 'application/json');
    // httpHeaders.append('Authorization', token);
    const message = data.form.value.message;

    console.log(httpHeaders);
    const userEmail = localStorage.getItem('userID');
    const sellerEmail = localStorage.getItem('sellerID');
    const sendData = {
      idClient : parseInt(userEmail),
      idSeller : parseInt(sellerEmail),
      message
    };
    console.log(sendData);
    this.http.post('https://back-end-hpp.herokuapp.com/api/v1/feedback', sendData, {headers: httpHeaders })
     .subscribe((result) => {
       console.warn('result', result);
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
      if (!this.wishlist.includes(this.houseID)) {
      this.wishlistService.addToWishlist(this.houseID).subscribe((result) => {
        console.log(result);
      });
      }
      else {
        this.wishlistService.removeFromWishlist(this.houseID).subscribe((result) => {
          console.log(result);

        });
      }
    }
    else{
      this.snackbar.open('Please Login!', 'Close', {
        duration: 3000
      })
    }
  }
}
