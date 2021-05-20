import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {House} from '../../../models/house';
import {WishlistService} from '../../../service/wishlist.service';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-house-item',
  templateUrl: './house-item.component.html',
  styleUrls: ['./house-item.component.css']
})
export class HouseItemComponent implements OnInit {
  @Input() item: House;

  @Input() addedToWishlist: boolean;
  // @Input() selected: boolean | undefined;
  // @Output() selectedChange = new EventEmitter<boolean>();
  constructor(private wishlistService: WishlistService,public _sanitizer: DomSanitizer) { }

  ngOnInit(): void {
  }
  // public toggleSelected() {
  //   this.selected = !this.selected;
  //   this.selectedChange.emit(this.selected); }
  defaultPhoto="/assets/house.png";


  handleAddToWishlist() {
    this.wishlistService.addToWishlist(this.item.houseID).subscribe((result) => {
      console.log(result);
      this.addedToWishlist = true;
    });
  }

  handleRemoveFromWishlist() {
    this.wishlistService.removeFromWishlist(this.item.houseID).subscribe((result) => {
      console.log(result);
      this.addedToWishlist = false;
    });
  }
}


