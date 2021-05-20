import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { NgxPaginationModule } from 'ngx-pagination';

import { ListComponent } from './list.component';

describe('ListComponent', () => {
  let component: ListComponent;
  let fixture: ComponentFixture<ListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientModule,RouterTestingModule,FormsModule,NgxPaginationModule],
      declarations: [ ListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should return all data',()=>{
    if(Storage==null)
       expect(component.totalRecords).toEqual(component.data);

  });
  it('initial state',()=>{
    expect(component.floor).toBeFalsy;
    expect(component.housing).toBeFalsy;
    expect(component.maxPrice).toBeFalsy;
    expect(component.minPrice).toBeFalsy;
    expect(component.noOfBathrooms).toBeFalsy;
    expect(component.noOfRooms).toBeFalsy;
    expect(component.surface).toBeFalsy;
  });

   it('choose floor',()=>{
     component.floor=5;
     expect(component.floor).toEqual(5);
  })

});
