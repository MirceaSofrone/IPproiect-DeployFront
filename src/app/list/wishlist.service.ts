import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {
url = 'https://house-prediction-fii.herokuapp.com/api/v1/users/';
  constructor(private http: HttpClient) { }

  getWishlist() {

    return this.http.get(this.url + 'getfavorite/' + localStorage.getItem('userID')).pipe(
      map((result: any[]) => {
        const productIds = [];

        result.forEach(item => productIds.push(item.houseID));

        return productIds;
      })
    );
  }

  addToWishlist(houseID) {
    console.log(this.url,"add to wishlist");
    return this.http.put(this.url + 'addtofavorite/' + localStorage.getItem('userID') + '/' + houseID, null);
  }

  removeFromWishlist(houseID) {
    console.log(this.url,"remove from wishlist");
    return this.http.delete(this.url + 'removefromfavorite/' + localStorage.getItem('userID') + '/' + houseID);
  }
}
