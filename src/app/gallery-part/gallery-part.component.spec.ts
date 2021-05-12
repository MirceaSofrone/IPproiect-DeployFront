import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GalleryPartComponent } from './gallery-part.component';

describe('GalleryPartComponent', () => {
  let component: GalleryPartComponent;
  let fixture: ComponentFixture<GalleryPartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GalleryPartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GalleryPartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
