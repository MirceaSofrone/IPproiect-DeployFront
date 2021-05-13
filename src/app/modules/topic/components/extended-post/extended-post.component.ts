import { isNgTemplate } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import post from '../../../../_files/info.json';
import { FinalPost } from '../../../../core/final-post.model';
@Component({
  selector: 'app-extended-post',
  templateUrl: './extended-post.component.html',
  styleUrls: ['./extended-post.component.css']
})
export class ExtendedPostComponent implements OnInit {
 
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
