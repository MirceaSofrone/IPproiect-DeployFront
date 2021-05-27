import {Component, OnInit, Input, Output, EventEmitter, ChangeDetectorRef} from '@angular/core';
import {PostsService} from '../../service/posts.service';
import {NgxPaginationModule} from 'ngx-pagination';
import {Router} from '@angular/router';
import {DomSanitizer} from '@angular/platform-browser';
import {House} from '../../models/house';
import {WishlistService} from '../../service/wishlist.service';
import {NgForm} from '@angular/forms';
import {AuthenticationService} from '../../../auth/services/authentication/authentication.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  // productList: House[] = [
  //   new House('1', '1', '1', '1', 1, '1', '1', 1, '1', '1'),
  //   new House('2', '2', '2', '3', 3, '4', '5', 5, '5', '5'),
  //   new House('1', '1', '1', '1', 1, '1', '1', 1, '1', '1'),
  //   new House('1', '1', '1', '1', 1, '1', '1', 1, '1', '1')
  // ];
  productList: House[] = [];
  private  wishlist: number[] = [];

  data: any;
  totalRecords: any;
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
  value = undefined;
city = undefined;

  constructor( private authService: AuthenticationService, private wishlistService: WishlistService, private productService: PostsService, private router: Router, private _sanitizer: DomSanitizer, private changeDetectorRef: ChangeDetectorRef) {
  }

  ngOnInit() {
    this.renderPage(null);
  }



  renderPage(form: NgForm) {

    console.log('aici');
    window.scrollTo(0, 0);
    console.log('aici3');



    const myStorage = localStorage.getItem('search');

    let searchKey;


    if (myStorage != null) {
      searchKey = JSON.parse(myStorage);

      this.type = searchKey.type;

      this.string = searchKey.string;

      this.housing = searchKey.housing;

      this.typeAll = 1;
    }

    if (form !== null) {
      this.typeAll = 1;

      this.string = form.value.string;

      this.noOfRooms = form.value.noOfRooms;


      this.minPrice = form.value.minPrice;


      this.maxPrice = form.value.maxPrice;

      this.maxPrice = form.value.maxPrice;

      this.surface = form.value.surface;

      this.floor = form.value.floor;


      this.noOfBathrooms = form.value.noOfBathrooms;
      this.city = form.value.city;

    }










    this.productService.getPosts(
      this.typeAll,
      this.page,
      8,
      this.type,
      this.string,
      this.housing,
      this.noOfRooms, this.floor,
      this.surface, this.noOfBathrooms,
      this.minPrice, this.maxPrice, this.city).subscribe((result) => {

      this.data = result;

      this.productList = result.second;

      this.totalRecords = result.first;

      setTimeout(() => {
        window.scroll(0, 0);
      }, 0);

    }, (error) => {

      this.productList = [];
      this.totalRecords = 0;
    });
    if (this.authService.isAuthenticated()) {
      this.wishlistService.getWishlist().subscribe(result => {
        this.wishlist = result;

      });
    }
  }


  paginationChange(newPage: number) {
    this.page = newPage;
    console.log('aici');
    window.scrollTo(0, 0);
    console.log('aici3');
    // window.location.hash = '#top';

    this.renderPage(null);

  }

}



