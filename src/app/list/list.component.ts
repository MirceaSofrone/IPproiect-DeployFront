import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { PostsService } from './posts.service';
import {NgxPaginationModule} from 'ngx-pagination';
import {Router} from '@angular/router';
import {DomSanitizer} from '@angular/platform-browser';
import {NgForm} from '@angular/forms';
@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  @Input() selected: boolean | undefined;
  @Output() selectedChange = new EventEmitter<boolean>();

  data: any;
  totalRecords: 8;
  page = 1;
  typeAll = 0;

   type: any;
   string: any;
   housing: any;
  noOfRooms: any;
  floor: any;
  surface: any;
  noOfBathrooms: any;
  minPrice: any;
  maxPrice: any;



  constructor(private postData: PostsService, private router: Router, private _sanitizer: DomSanitizer) { }

  ngOnInit(): void {
 this.renderPage(null);
  }

  public toggleSelected() {
    this.selected = !this.selected;
    this.selectedChange.emit(this.selected); }

  renderPage(form: NgForm) {

    console.log(form, 'form');
    console.log;
    console.log(this.minPrice);
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
    if (form !== null){
      this.typeAll =1;
      if(form.value.string!="")
        this.string=form.value.string;
  if (form.value.noOfRooms != '') {
  this.noOfRooms = form.value.noOfRooms;
  }
  if (form.value.minPrice != '') {
    this.minPrice = form.value.minPrice;
  }
  if (form.value.maxPrice != '') {
this.maxPrice = form.value.maxPrice;
  }
      this.maxPrice = form.value.maxPrice;
  if (form.value.surface != '') {
    this.surface = form.value.surface;
  }
  if (form.value.floor != '') {
    this.floor = form.value.floor;
  }
  if (form.value.noOfBathrooms != '') {
    this.noOfBathrooms = form.value.noOfBathrooms;
  }

}
    console.log(this.typeAll,"typeall")
    console.log(this.type)
    console.log(this.string);
    console.log(this.housing)
    console.log(this.noOfRooms);
    console.log(this.minPrice);
    console.log(this.maxPrice);
    console.log(this.surface);
    console.log(this.floor);
    console.log(this.noOfBathrooms);
    this.postData.getPosts(this.typeAll, this.page, 8, this.type, this.string, this.housing, this.noOfRooms, this.floor, this.surface, this.noOfBathrooms, this.minPrice, this.maxPrice).subscribe((result) => {console.warn('AICI CALL', result);
                                                                                                                                                                                                       this.data = result;
                                                                                                                                                                                                       console.log(result);
                                                                                                                                                                                                       this.totalRecords = this.data.length;
                                                                                                                                                                                                       console.log(this.totalRecords); });
    console.log(this.totalRecords, 'total totalRecords');
    console.log(this.data,"dataAaaaa");
  }

}




