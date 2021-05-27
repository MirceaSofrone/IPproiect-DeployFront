import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExtendedPostComponent } from './extended-post.component';

describe('ExtendedPostComponent', () => {
  let component: ExtendedPostComponent;
  let fixture: ComponentFixture<ExtendedPostComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExtendedPostComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExtendedPostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
