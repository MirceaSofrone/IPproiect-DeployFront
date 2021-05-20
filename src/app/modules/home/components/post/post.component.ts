import { Component, OnInit } from '@angular/core';
import info from '../../../../_files/info.json';
import { ForumPost } from '../../../../model/forum-post';
import { PostResolverService } from './post-resolver.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  public forumPosts: ForumPost[];

  constructor(private postService: PostResolverService) { }


  ngOnInit(): void {
    this.postService.findAll().subscribe((data: ForumPost[]) => {
      this.forumPosts = data;
    });
  }

  // infoList:{

  //   id:string,
  //   username:string,
  //   question:string,
  //   answers:string,
  //   text:string
  //   }[]=info;


}
