import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {IFavourite} from './favourite';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-favorite-panel',
  templateUrl: './favorite-panel.component.html',
  styleUrls: ['./favorite-panel.component.css']
})
export class FavoritePanelComponent {
  URL = 'https://house-prediction-fii.herokuapp.com/api/v1/users/getfavorite/6757fff1-e437-4d23-bd45-646a4b419b16';
  favData: any[] = [];
  favIndex = 1;
  
  deleteURL = "https://house-prediction-fii.herokuapp.com/api/v1/users/removefromfavorite";
  constructor(private http: HttpClient, public _sanitizer: DomSanitizer) {
    this.http.get<IFavourite[]>(this.URL).subscribe(
      data => this.favData = data,
      () => {},
      );
    }
    
    remove(): void {
      this.http.put(this.deleteURL, 
        {houseID: '83929af5-bcdf-4afa-a1c2-917c4d76667d', 
        userID: '6757fff1-e437-4d23-bd45-646a4b419b16'});
        console.log("removed");
      }
      
  //https://house-prediction-fii.herokuapp.com/api/v1/users/addtofavorite/6757fff1-e437-4d23-bd45-646a4b419b16/83929af5-bcdf-4afa-a1c2-917c4d76667d
  //https://house-prediction-fii.herokuapp.com/api/v1/users/history?userID=6757fff1-e437-4d23-bd45-646a4b419b16
  //https://house-prediction-fii.herokuapp.com/api/v1/users/getfavorite/6757fff1-e437-4d23-bd45-646a4b419b16
  //https://house-prediction-fii.herokuapp.com/api/v1/users/removefromfavorite/6757fff1-e437-4d23-bd45-646a4b419b16/83929af5-bcdf-4afa-a1c2-917c4d76667d

  favPrev(): void {
    if (this.favIndex - 1 !== 0) {
      this.favIndex = this.favIndex - 1;
    }
  }

  favNext(): void {
    if (this.favIndex + 1 !== this.favData.length - 1) {
      this.favIndex = this.favIndex + 1;
    }
  }

}
