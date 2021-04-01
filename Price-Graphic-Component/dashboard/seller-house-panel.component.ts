import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-seller-house-panel',
  templateUrl: './seller-house-panel.component.html',
  styleUrls: ['./seller-house-panel.component.css']
})
export class SellerHousePanelComponent implements OnInit {
  title = 'Lorem.';
  content = 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Nemo vero natus velit labore? Iusto non perferendis nam excepturi inventore temporibus sed culpa!';
  image = 'https://www.architectureartdesigns.com/wp-content/uploads/2015/07/713.jpg';
  constructor() { }

  ngOnInit(): void {
  }

}
