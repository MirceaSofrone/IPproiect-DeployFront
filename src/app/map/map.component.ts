import { Component, OnInit } from '@angular/core';
import { AgmCoreModule } from '@agm/core';
import { MapsAPILoader } from '@agm/core';
@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {
  lat = 47.15859450366613;
  long = 27.620025261296533;
  circles: circle[] = [
	  {
		  lat: 47.17571669460426,
		  lng: 27.54768426376942,
		  
	  },
	  {
		  lat: 47.156249132241435,
		  lng: 27.58454429702573,
		 
	  },
	  {
		  lat: 47.17916843850815,
		  lng: 27.555534824875878,
		 
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