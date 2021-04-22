import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http'
@Injectable({
  providedIn: 'root'
})
export class PostsService {
  url="https://607af93abd56a60017ba3474.mockapi.io/api/v1/emails"

  constructor(private http:HttpClient) { }
  getPosts()
  {
    return this.http.get(this.url);
  }
}

