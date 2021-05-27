import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {House} from '../../../models/house';
import {WishlistService} from '../../../service/wishlist.service';
import {DomSanitizer} from '@angular/platform-browser';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../../../auth/services/authentication/authentication.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-house-item',
  templateUrl: './house-item.component.html',
  styleUrls: ['./house-item.component.css']
})
export class HouseItemComponent implements OnInit {
  // @Input() selected: boolean | undefined;
  // @Output() selectedChange = new EventEmitter<boolean>();
  constructor( private snackbar: MatSnackBar,private wishlistService: WishlistService, public _sanitizer: DomSanitizer, private router: Router,private authService: AuthenticationService) { }
  @Input() item: House;

  @Input() addedToWishlist: boolean;
  // public toggleSelected() {
  //   this.selected = !this.selected;
  //   this.selectedChange.emit(this.selected); }
  defaultPhoto = '/assets/house.png';

  ngOnInit(): void {
  }
  onClick(id,userid){
    localStorage.setItem('sellerID', userid);
    localStorage.setItem('houseID', id);
    this.router.navigateByUrl('/house-details/' + id);
  }

  handleAddToWishlist() {
    if (this.authService.isAuthenticated()) {
      this.wishlistService.addToWishlist(this.item.houseID).subscribe((result) => {

        this.addedToWishlist = true;
      });
    }
    else{
      this.snackbar.open('Please Login!', 'Close', {
        duration: 3000
      })
    }
  }

  handleRemoveFromWishlist() {
    if (this.authService.isAuthenticated()) {
      this.wishlistService.removeFromWishlist(this.item.houseID).subscribe((result) => {

        this.addedToWishlist = false;
      });
    }
    else{
      this.snackbar.open('Please Login!', 'Close', {
        duration: 3000
      })
    }
  }
}


