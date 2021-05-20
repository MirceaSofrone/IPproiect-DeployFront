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
    let params = new HttpParams()
      .set('page', page)
      .set('number', number);


    if(typeAll===1)
    {
      this.url="https://house-prediction-fii.herokuapp.com/api/v1/search";
      console.log(noOfRooms,"typee");
      if(type!=undefined) {
        console.warn("doferot de type")
        // params=params.set('houseType', type);
      }

      if(string!=undefined)
        params=  params.set('text', string);
      if(housing!=undefined)
        params.set('sellType', housing);
      if(noOfRooms!=undefined)
        params=  params.set('noOfRooms', noOfRooms);
      if(floor!=undefined)
        params=  params.set('floor', floor);
      if(surface!=undefined)
        params=  params.set('surface',surface);
      if(noOfBathrooms!=undefined)
        params=  params.set('noOfBathrooms', noOfBathrooms);
      if(minPrice!=undefined)
        params=   params.set('minPrice', minPrice);
      if(maxPrice!=undefined)
        params=  params.set('maxPrice', maxPrice);

    }


    console.log(this.url);
    console.log(params.toString(),"PARAMAS");
    return this.http.get(this.url, {params});
  }
}
