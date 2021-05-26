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
  favData: any[] = [];
  favIndex = 1;


  URL = 'https://back-end-hpp.herokuapp.com/api/v1/users/getfavorite/';
  deleteURL = "https://back-end-hpp.herokuapp.com/api/v1/users/removefromfavorite/";
  constructor(private http: HttpClient, public _sanitizer: DomSanitizer) {
    const headers = { 'Authorization': 'Bearer ' + localStorage.getItem('token')};
    this.http.get<IFavourite[]>(this.URL + localStorage.getItem('userID'), { headers }).subscribe(
      data => this.favData = data,
      () => {},
      );
    }

    remove(id: string, index: number): void {
      const headers = { 'Authorization': 'Bearer ' + localStorage.getItem('token') };
      this.http.delete<any>(this.deleteURL + localStorage.getItem('userID') + '/' + id, { headers }).subscribe(
          { next:(result) =>{ console.log(result);},
            error:(err:any) => {console.log(err);}, 
            complete:()=> { console.log("complete");
                            this.favData.splice(index, 1);}
          }
        );
      }

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
