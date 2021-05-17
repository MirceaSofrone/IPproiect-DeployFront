import { Injectable } from '@angular/core';
import{HttpClient} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class GalleryService {
  constructor(private http:HttpClient) { }
  getPosts()
  {
    let url="https://picsum.photos/v2/list";
    return this.http.get(url);
  }
}
