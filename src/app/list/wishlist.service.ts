import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {
wishlistUrl: 'https://house-prediction-fii.herokuapp.com/api/users/';
  constructor(private http: HttpClient) { }

  getWishlist() {
    return this.http.get(this.wishlistUrl + 'getfavorite/' + localStorage.getItem('userID')).pipe(
      map((result: any[]) => {
        const productIds = [];

        result.forEach(item => productIds.push(item.houseID));

        return productIds;
      })
    );
  }

  addToWishlist(houseID) {
    return this.http.post(this.wishlistUrl + 'addtofavorite/' + localStorage.getItem('userID') + '/' + houseID, null);
  }

  removeFromWishlist(houseID) {
    return this.http.delete(this.wishlistUrl + 'removefromfavorite' + localStorage.getItem('userID') + '/' + houseID);
  }
}
