import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { PostResolverService } from '../../../home/components/post/post-resolver.service'
import { ForumPost } from 'src/app/model/forum-post';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent implements OnInit {

  forumPost: ForumPost;

  constructor(private _location: Location, private postService: PostResolverService) {
    this.forumPost = new ForumPost();
  }

  ngOnInit(): void {
  }

  goBack() {
    this._location.back();
  }

  onSubmit(event: any) {
    this.forumPost.content = event.target.content.value;
    this.forumPost.author = {
      userID: "bd8839d0-0e92-4812-a817-2e592348c5bf",
      firstName: "Anonymous",
      lastName: ""
    };
    this.postService.save(this.forumPost).subscribe(result => this.goBack());
  }

}
