import { Component, OnInit } from '@angular/core';
import { PostsService } from './posts.service'
@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
   data:any
  constructor(private postData:PostsService) { }

  ngOnInit(): void {
    this.postData.getPosts().subscribe((result)=>{console.warn("result",result)
  this.data=result})
  }

}
