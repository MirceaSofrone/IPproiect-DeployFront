import { Component, OnInit,Input, Output, EventEmitter } from '@angular/core';
import { PostsService } from './posts.service'
@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  @Input() selected: boolean | undefined;
  @Output() selectedChange = new EventEmitter<boolean>();
   data:any
   totalRecords: number | undefined
   page: number=1
  constructor(private postData:PostsService) { }

  ngOnInit(): void {
    this.postData.getPosts().subscribe((result)=>{console.warn("result",result)
    this.data=result
    this.totalRecords=this.data.result.length;})
  
  }
  public toggleSelected() {
    this.selected = !this.selected;
    this.selectedChange.emit(this.selected)}
  }

