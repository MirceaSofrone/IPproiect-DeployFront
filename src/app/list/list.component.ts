import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { PostsService } from './posts.service';
import {NgxPaginationModule} from 'ngx-pagination';
import {Router} from '@angular/router';
import {DomSanitizer} from '@angular/platform-browser';
import {House} from './house';
import {WishlistService} from './wishlist.service';
@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  productList: House[] = [];
  wishlist: number[] = [];

  data: any;
  totalRecords: 5;
  page = 1;
  typeAll = 0;

   type = undefined;
   string = undefined;
   housing = undefined;
  noOfRooms = undefined;
  floor = undefined;
  surface = undefined;
  noOfBathrooms = undefined;
  minPrice = undefined;
  maxPrice = undefined;



  constructor(private wishlistService: WishlistService, private productService: PostsService, private router: Router, private _sanitizer: DomSanitizer) { }

  async ngOnInit() {


    const myStorage = localStorage.getItem('search');
    console.warn(myStorage);
    let searchKey;




    if (myStorage != null){
      searchKey = JSON.parse(myStorage);
      console.warn(searchKey);
      this.type = searchKey.type;
      console.warn(this.type);
      this.string = searchKey.string;
      console.warn(this.string);
      this.housing = searchKey.housing;
      console.warn(this.housing);
      this.typeAll = 1;
    }
    delay(3000);
    await this.productService.getPosts(this.typeAll, this.page, 8, this.type, this.string, this.housing, this.noOfRooms, this.floor, this.surface, this.noOfBathrooms, this.minPrice, this.maxPrice).subscribe((result) => {


                                                                                                                                                                                                                      this.productList = result;
                                                                                                                                                                                                                      console.log(this.productList, 'this productlist');


    });
    delay(3000);
    await   this.wishlistService.getWishlist().subscribe(productIds => {
      this.wishlist = productIds;
      console.log(this.wishlist);
    });

    console.log(this.productList, 'prod');
    console.log(this.wishlist, 'wish');
}

}

function delay(ms: number) {
  return new Promise( resolve => setTimeout(resolve, ms) );
}


