import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPendingAllocationComponent } from './pending-allocations.component';

describe('ViewAllocationComponent', () => {
  let component: ViewPendingAllocationComponent;
  let fixture: ComponentFixture<ViewPendingAllocationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewPendingAllocationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewPendingAllocationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
