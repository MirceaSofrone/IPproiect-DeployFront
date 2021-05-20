import { Component } from '@angular/core';

import {PostsService } from './posts.service';


// import { PostsService} from './posts.service'
//
// // import{TestService} from './test.service';
// import{HttpClient} from '@angular/common/http';
// import{PhotoCarouselService} from './service/photo-carousel.service'
// import { PostsService } from './posts.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {

 
//   constructor(private postData: PostsService){}
//   ngOnInit()
//   {
//     this.postData.getPosts().subscribe((result)=>{
//      console.warn("result",result) 
//      this.data=result
//     })
//   }
  

  
  data: any;
  title = 'IPproiect';





  // data:any;
  // constructor(private postData:PhotoCarouselService) {}
  // ngOnInit() {
  //   this.postData.getPosts().subscribe((result)=>{
  //     console.warn("result", result);
  //     this.data=result;
  //   })
  // }

}
