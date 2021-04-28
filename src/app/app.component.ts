import { Component } from '@angular/core';
import {PostsService } from './posts.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
<<<<<<< HEAD:gallery/src/app/app.component.ts
  title = 'gallery';
  data:any;
  constructor(private postData: PostsService){}
  ngOnInit()
  {
    this.postData.getPosts().subscribe((result)=>{
     console.warn("result",result) 
     this.data=result
    })
  }
=======

  title = 'IPproiect';


>>>>>>> 6171a5cb394f450b1f6108e6f03325372461a919:src/app/app.component.ts
}
