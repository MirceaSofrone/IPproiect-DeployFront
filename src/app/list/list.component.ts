import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { PostsService } from './posts.service';
import {NgxPaginationModule} from 'ngx-pagination';
import {Router} from '@angular/router';
import {DomSanitizer} from '@angular/platform-browser';
@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  @Input() selected: boolean | undefined;
  @Output() selectedChange = new EventEmitter<boolean>();
  data: any;
  totalRecords: number | undefined;
  page = 1;
  typeAll = 0;

   type = undefined;
   string = undefined;
   housing = undefined;
  noOfRooms=undefined;
  floor=undefined;
  surface=undefined;
  noOfBathrooms=undefined;
  minPrice=undefined;
  maxPrice=undefined;



  constructor(private postData: PostsService, private router: Router, private _sanitizer: DomSanitizer) { }

  ngOnInit(): void {
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

    this.postData.getPosts(this.typeAll, this.page, 8,this.type,this.string,this.housing,this.noOfRooms,this.floor,this.surface,this.noOfBathrooms,this.minPrice,this.maxPrice).subscribe((result) => {console.warn('AICI CALL', result);
                                                                             this.data = result;
                                                                             console.log(result);
                                                                             this.totalRecords = this.data.length;
                                                                             console.log(this.totalRecords); });


  }

  public toggleSelected() {
    this.selected = !this.selected;
    this.selectedChange.emit(this.selected); }
}




