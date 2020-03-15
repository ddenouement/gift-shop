import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WishlistMyComponent } from './wishlist-my.component';

describe('WishlistMyComponent', () => {
  let component: WishlistMyComponent;
  let fixture: ComponentFixture<WishlistMyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WishlistMyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WishlistMyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
