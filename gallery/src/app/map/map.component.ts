import { Component, OnInit } from '@angular/core';
import { AgmCoreModule } from '@agm/core';
import { MapsAPILoader } from '@agm/core';
@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {
  lat=47.17571669460426;
  lng=27.54768426376942;
  zoom=16;
  circles: circle[] = [
	  {
		  lat: 47.17571669460426,
		  lng: 27.54768426376942,
		  
	  }
  ]

  constructor(
    private _mapsAPILoader: MapsAPILoader
  ) { }

  ngOnInit(): void {
  }

}
interface circle {
	lat: number;
	lng: number;}