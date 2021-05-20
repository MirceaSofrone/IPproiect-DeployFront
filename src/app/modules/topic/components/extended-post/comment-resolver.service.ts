import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ForumComment } from 'src/app/model/forum-comment';
import { Router } from '@angular/router';
import { ForumPost } from 'src/app/model/forum-post';

@Injectable({
  providedIn: 'root'
})
export class CommentResolverService {

  private backendurl: string;

  constructor(private http: HttpClient, private router: Router) { this.backendurl = 'http://localhost:8090/api/v1/forum' }

  public findAll(): Observable<ForumPost> {
    let explodedURL = this.router.url.split('/');
    return this.http.get<ForumPost>(this.backendurl + '/' + explodedURL[explodedURL.length - 1]);
  }
}
