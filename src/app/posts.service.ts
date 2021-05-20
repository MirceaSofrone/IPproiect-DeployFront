import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class PostsService {
url = 'https://picsum.photos/v2/list';
  constructor(private http: HttpClient) { }
  getPosts()
  {
    return this.http.get(this.url);
  }
}
