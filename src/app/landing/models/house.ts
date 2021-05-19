export class House {

  houseID: any;
  img: any;
  title: string;
  city: string;
  noOfRooms: number;
  surface: string;
  type: string;
  currentPrice: number;
  imageUrl: string;
  sellType: any;


  constructor(houseID: any, img: any, title: string, city: string, noOfRooms: number, surface: string, type: string, currentPrice: number, imageUrl: string, sellType: any) {
    this.houseID = houseID;
    this.img = img;
    this.title = title;
    this.city = city;
    this.noOfRooms = noOfRooms;
    this.surface = surface;
    this.type = type;
    this.currentPrice = currentPrice;
    this.imageUrl = imageUrl;
    this.sellType = sellType;
  }
}
