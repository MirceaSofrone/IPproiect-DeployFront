import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PostsService {
  url = 'https://house-prediction-fii.herokuapp.com/api/v1/allhouses';

  constructor(private http: HttpClient) {
  }

  getPosts(typeAll,page, number,type,string,housing,noOfRooms,floor,surface,noOfBathrooms,minPrice,maxPrice) {
    const params = new HttpParams()
      .set('page', page)
      .set('number', number);
    if(typeAll===1)
    {
      this.url="https://house-prediction-fii.herokuapp.com/api/v1/search";
      if(type!=undefined)
        params.set('houseType', type);
      if(string!=undefined)
        params.set('search', string);
      if(housing!=undefined)
        params.set('sellType', housing);
      if(noOfRooms!=undefined)
        params.set('noOfRooms', noOfRooms);
      if(floor!=undefined)
        params.set('floor', floor);
      if(surface!=undefined)
        params.set('surface',surface);
      if(noOfBathrooms!=undefined)
        params.set('noOfBathrooms', noOfBathrooms);
      if(minPrice!=undefined)
        params.set('minPrice', minPrice);
      if(maxPrice!=undefined)
        params.set('maxPrice', maxPrice);

    }


    console.log(this.url);
    return this.http.get(this.url, {params});
  }
}
