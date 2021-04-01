import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StatisticiBtnComponent } from './statistici-btn.component';

describe('StatisticiBtnComponent', () => {
  let component: StatisticiBtnComponent;
  let fixture: ComponentFixture<StatisticiBtnComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StatisticiBtnComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StatisticiBtnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
