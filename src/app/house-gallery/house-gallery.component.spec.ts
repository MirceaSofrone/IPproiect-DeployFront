import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HouseGalleryComponent } from './house-gallery.component';

describe('HouseGalleryComponent', () => {
  let component: HouseGalleryComponent;
  let fixture: ComponentFixture<HouseGalleryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HouseGalleryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HouseGalleryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
