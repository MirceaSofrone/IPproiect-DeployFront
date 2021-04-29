import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { PostsService } from './posts.service';
import {NgxPaginationModule} from "ngx-pagination";
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
  constructor(private postData: PostsService) { }

  ngOnInit(): void {
    const myStorage = localStorage.getItem('search');
    console.warn(myStorage);
    let searchKey;
    let type;
    let string;
    let housing;


    if (myStorage != null){
      searchKey = JSON.parse(myStorage);
      console.warn(searchKey);
      type = searchKey.type;
      console.warn(type);
      string = searchKey.string;
      console.warn(string);
      housing = searchKey.housing;
      console.warn(housing);
    }

    this.postData.getPosts().subscribe((result) => {console.warn('result', result);
                                                    this.data = result;
                                                    this.totalRecords = this.data.length; });


  }

  public toggleSelected() {
    this.selected = !this.selected;
    this.selectedChange.emit(this.selected); }
}





