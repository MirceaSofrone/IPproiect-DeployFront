import { Component, OnInit } from '@angular/core';
import post from '../../../../_files/info.json';
import { FinalPost } from '../../../../core/final-post.model';
import { Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-forum-topic',
  templateUrl: './forum-topic.component.html',
  styleUrls: ['./forum-topic.component.css']
})
export class ForumTopicComponent implements OnInit {

  finalPost: FinalPost | undefined;
  private routeSub: Subscription = new Subscription;

   givenPost:{
    id:string,
    username:string,
    question:string,
    answers:string,
    description:string,
    text:string
     }[]=post;

 constructor(private route: ActivatedRoute) { 
 }

 ngOnInit(): void {
   this.routeSub = this.route.params.subscribe(params => {
     const givenId = params['id']; //the value of id
     this.finalPost = this.givenPost.find(item => item.id === givenId)
   });
 }

 ngOnDestroy() {
   this.routeSub.unsubscribe();
 }


}
