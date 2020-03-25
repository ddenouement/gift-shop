import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderViewAdminComponent } from './order-view-admin.component';

describe('OrderViewAdminComponent', () => {
  let component: OrderViewAdminComponent;
  let fixture: ComponentFixture<OrderViewAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrderViewAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderViewAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
