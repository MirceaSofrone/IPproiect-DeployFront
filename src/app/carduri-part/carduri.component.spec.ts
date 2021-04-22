import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarduriComponent } from './carduri.component';

describe('LandingSearchComponent', () => {
  let component: CarduriComponent;
  let fixture: ComponentFixture<CarduriComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CarduriComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CarduriComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
