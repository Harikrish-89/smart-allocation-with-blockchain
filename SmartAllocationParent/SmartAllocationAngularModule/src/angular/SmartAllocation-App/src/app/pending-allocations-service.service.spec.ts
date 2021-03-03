import { TestBed, inject } from '@angular/core/testing';

import { ViewPendingAllocationServiceService } from './pending-allocations-service.service';

describe('ViewAllocationServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ViewPendingAllocationServiceService]
    });
  });

  it('should be created', inject([ViewPendingAllocationServiceService], (service: ViewPendingAllocationServiceService) => {
    expect(service).toBeTruthy();
  }));
});

