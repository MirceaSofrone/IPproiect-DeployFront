import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { HouseInfoComponent } from './house-info.component';

describe('HouseInfoComponent', () => {
  let component: HouseInfoComponent;
  let fixture: ComponentFixture<HouseInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule,HttpClientModule],
      declarations: [ HouseInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HouseInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  // it('initial state',()=>{
  //   component.houseID=null;
  //   expect(component.houseID).toBeNull;
  // })
});
