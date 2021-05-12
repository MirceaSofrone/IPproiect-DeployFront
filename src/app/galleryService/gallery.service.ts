import { Injectable } from '@angular/core';
import{HttpClient} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class GalleryService {
  constructor(private http:HttpClient) { }
  getPosts()
  {
    // let url="https://house-prediction-fii.herokuapp.com/api/v1/housesPhotos/8929e157-598d-40b8-b238-804ed7d6c3a4";
    let url="https://picsum.photos/v2/list";
    return this.http.get(url);
  }
}
