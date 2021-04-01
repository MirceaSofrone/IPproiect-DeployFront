import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FavouriteEstatesSectionComponent } from './favourite-estates-section.component';

describe('FavouriteEstatesSectionComponent', () => {
  let component: FavouriteEstatesSectionComponent;
  let fixture: ComponentFixture<FavouriteEstatesSectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FavouriteEstatesSectionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FavouriteEstatesSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
