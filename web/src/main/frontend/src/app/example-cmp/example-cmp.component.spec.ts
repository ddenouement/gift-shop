import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExampleCmpComponent } from './example-cmp.component';

describe('ExampleCmpComponent', () => {
  let component: ExampleCmpComponent;
  let fixture: ComponentFixture<ExampleCmpComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExampleCmpComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExampleCmpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
