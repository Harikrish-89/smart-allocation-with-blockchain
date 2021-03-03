import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifyAllocationComponent } from './modify-allocation.component';

describe('ModifyAllocationComponent', () => {
  let component: ModifyAllocationComponent;
  let fixture: ComponentFixture<ModifyAllocationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModifyAllocationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifyAllocationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
