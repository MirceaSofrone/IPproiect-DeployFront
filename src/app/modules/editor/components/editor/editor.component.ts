import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { CommentResolverService } from '../../../topic/components/extended-post/comment-resolver.service'
import { ForumComment } from 'src/app/model/forum-comment';
@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css']
})
export class EditorComponent implements OnInit {

  forumComment: ForumComment;

  constructor(private _location: Location, private commentService: CommentResolverService) {
    this.forumComment = new ForumComment();
  }

  ngOnInit(): void {
  }

  onSubmit(event: any) {
    this.forumComment.content = event.target.content.value;
    this.forumComment.author = {
      userID: "bd8839d0-0e92-4812-a817-2e592348c5bf",
      firstName: "Anonymous",
      lastName: ""
    };
    this.commentService.save(this.forumComment).subscribe(result => this.goBack());
  }

  goBack() {
    this._location.back();
  }

}
