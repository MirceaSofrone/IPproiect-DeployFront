import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ForumPost } from '../../../../model/forum-post';

@Injectable({
  providedIn: 'root'
})
export class PostResolverService {

  private backendurl: string;

  constructor(private http: HttpClient) { this.backendurl = 'http://localhost:8090/api/v1/forum' }

  public findAll(): Observable<ForumPost[]> {
    return this.http.get<ForumPost[]>(this.backendurl);
  }

  public save(post: ForumPost) {
    return this.http.post<ForumPost>(this.backendurl, post);
  }
}
