import { Component, OnInit } from '@angular/core';
import info from '../../../../_files/info.json';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  infoList:{
    
    id:string,
    username:string,
    question:string,
    answers:string,
    text:string
    }[]=info;

  
}
