import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeAllocationComponent } from './change-allocation.component';

describe('ChangeAllocationComponent', () => {
  let component: ChangeAllocationComponent;
  let fixture: ComponentFixture<ChangeAllocationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChangeAllocationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeAllocationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
