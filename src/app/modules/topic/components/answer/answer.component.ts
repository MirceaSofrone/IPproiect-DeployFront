import { Component, OnInit } from '@angular/core';
import post from '../../../../_files/info.json';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-answer',
  templateUrl: './answer.component.html',
  styleUrls: ['./answer.component.css']
})
export class AnswerComponent implements OnInit {


   infoList:{
    id:string,
    username:string,
    question:string,
    answers:string,
    text:string
    }[]=post;

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    const routeParams = this.route.snapshot.paramMap;
    const postIdFromRoute = String(routeParams.get('id'));
    this.route.data.subscribe(data => {
      this.infoList = data.get('id'); 
    });
  }

}
