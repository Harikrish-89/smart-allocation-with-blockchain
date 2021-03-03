import { TestBed, inject } from '@angular/core/testing';

import { ApprovedAllocationServiceService } from './approved-allocation-service.service';

describe('ApprovedAllocationServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ApprovedAllocationServiceService]
    });
  });

  it('should be created', inject([ApprovedAllocationServiceService], (service: ApprovedAllocationServiceService) => {
    expect(service).toBeTruthy();
  }));
});
