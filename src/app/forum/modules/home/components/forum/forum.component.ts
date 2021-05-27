import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-forum',
  templateUrl: './forum.component.html',
  styleUrls: ['./forum.component.css']
})
export class ForumComponent implements OnInit {

  private routeSub: Subscription = new Subscription;
  constructor(private route: ActivatedRoute) { 
  }
  
  ngOnInit(): void {
  }

  ngOnDestroy() {
    this.routeSub.unsubscribe();
  }
}
