import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchAllocationComponent } from './search-allocation.component';

describe('SearchAllocationComponent', () => {
  let component: SearchAllocationComponent;
  let fixture: ComponentFixture<SearchAllocationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchAllocationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchAllocationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
