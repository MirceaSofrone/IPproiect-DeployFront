import { isNgTemplate } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import post from '../../../../_files/info.json';
import { FinalPost } from '../../../../core/final-post.model';
import { CommentResolverService } from './comment-resolver.service';
import { ForumComment } from 'src/app/model/forum-comment';
import { ForumPost } from 'src/app/model/forum-post';

@Component({
  selector: 'app-extended-post',
  templateUrl: './extended-post.component.html',
  styleUrls: ['./extended-post.component.css']
})
export class ExtendedPostComponent implements OnInit {

  // finalPost: FinalPost | undefined;

  private routeSub: Subscription = new Subscription;

  forumPost: ForumPost | undefined;

  constructor(private route: ActivatedRoute, private commentService: CommentResolverService) {
  }

  ngOnInit(): void {

    this.commentService.findAll().subscribe((data: ForumPost) => {
      this.forumPost = data;
    });
  }


}
