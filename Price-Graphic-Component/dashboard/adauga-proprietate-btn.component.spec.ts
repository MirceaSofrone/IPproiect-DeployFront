import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdaugaProprietateBtnComponent } from './adauga-proprietate-btn.component';

describe('AdaugaProprietateBtnComponent', () => {
  let component: AdaugaProprietateBtnComponent;
  let fixture: ComponentFixture<AdaugaProprietateBtnComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdaugaProprietateBtnComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdaugaProprietateBtnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
