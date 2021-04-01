import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocuinteBtnComponent } from './locuinte-btn.component';

describe('LocuinteBtnComponent', () => {
  let component: LocuinteBtnComponent;
  let fixture: ComponentFixture<LocuinteBtnComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LocuinteBtnComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LocuinteBtnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
